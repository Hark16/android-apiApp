package com.example.api1

import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var button: Button
    lateinit var textView: TextView
    lateinit var progressBar: ProgressBar




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)

        progressBar.visibility = View.GONE

        button.setOnClickListener {

        progressBar.visibility = View.VISIBLE

            getJoke("give me a joke on base of this "+ editText.text.toString())
        }
    }

    private fun getJoke(query: String) {

//below apiKey variable will be also use in apiInterface file in sending data in header  from post methode
        val apiKey = "write your api key here"

//below base url is that url which we get from our api provider and the base url is till .com or .in or etc extaintion of domain also we put slash  at last of base url and after slash the rest of url is our endpoint which we use in post or get methode in our api interface file 

        val baseUrl = "https://api.openai.com/"

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

//below we are using our api interface file
        val service = retrofit.create(ApiInterface::class.java)

//below requestData variable will also be use in apiInterface file as body of sending data from post methode
        val requestData = 
//below jokeRequest is data class which we defined in jokeRequest.kt file

JokeRequest(
            model = "gpt-3.5-turbo",
            messages = listOf(

//below requestMessage is the sub data class  which we defind in jokeRequest data class in jokeRequest.kt file

                RequestMessage(role = "user", content = query)
            )
        )

//below line is related to api interface file read that 
        service.getJoke1(apiKey, requestData).enqueue(object : Callback<JokeResponse> {

//below our api call is successful
            override fun onResponse(call: Call<JokeResponse>, response: Response<JokeResponse>) {
                if (response.isSuccessful) {
//here in response we are getting response 
                    val data = response.body()
                    data?.let {

//if our data is not null then we show the data in our textview
        progressBar.visibility = View.GONE

                        textView.text = it.choices[0].message.content
                    }
                } else {

//here our api call is successful but due to some reason we fail to get right response
//reason can be anything like api key is wrong or sending data is wrong or sending data format is wrong
//for this error we will check api key, joke request or joke response files or api interface file or main activity 
        progressBar.visibility = View.GONE

//                    textView.text = "Failed to get joke: ${response.message()}"
                    textView.text = "Failed to get joke"

                }
            }

//here our api call is not successful or failed
            override fun onFailure(call: Call<JokeResponse>, t: Throwable) {
//                textView.text = "Error: ${t.message}"
        progressBar.visibility = View.GONE

                textView.text = "Error"

            }
        })
    }
}
