package com.example.api1

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("v1/chat/completions")
    fun getJoke1(

// ,  key : value format below like apiKey is key  and after colon is value same as requestdata is key and jokeRequest is value so key goes to main activity file and value will go to request file here is jokeRequest file
//also the key(we are talking on above comment) are variable of mainactivity file like apiKey and requestData  both are variable in mainActivity file

// we are sending this all data to our api provider which is chat gpt website in this time
//check jokeRequest file for more detail for this block 

//and after peranthisis we will get the data from chat gpt website
// read next below comment

        @Header("Authorization") apiKey: String, //here apiKey is variable of mainActivity file and the string is its data type
        @Body requestData: JokeRequest // here requestData is variable of mainnActivity file and jokeRequest is data class which is written in jokeRequest.kt file 
    )
//here below we are getting data from our api call in this time from chat gpt website 
//below we are getting the response
//check jokeResponse file for more detail


: Call<JokeResponse>
}
