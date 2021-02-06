package com.app.interview.repository.parser

import com.app.interview.repository.network.CartResponse
import com.google.gson.Gson
import javax.inject.Inject

class ResponseParser @Inject constructor(private val gson: Gson) {
    fun parseProducts(response: String): CartResponse {
        return gson.fromJson(response, CartResponse::class.java)
    }
}