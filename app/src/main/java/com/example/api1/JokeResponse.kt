// JokeResponse.kt
package com.example.api1

data class JokeResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: ResponseMessage
)

data class ResponseMessage(
    val content: String
)
