// JokeRequest.kt
package com.example.api1

data class JokeRequest(
    val model: String,


//below variable is using requestMessage name data class which will be define below and we use it into our mainActivity file

    val messages: List<RequestMessage>
)

data class RequestMessage(
    val role: String,
    val content: String
)
