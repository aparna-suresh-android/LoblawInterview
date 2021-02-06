package com.app.interview.repository.network

import com.app.interview.repository.RepoResult
import com.app.interview.repository.entity.Product
import com.app.interview.repository.parser.ResponseParser
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class Network @Inject constructor(
    private val mNetworkClient: OkHttpClient,
    private val mParser: ResponseParser
) {

    companion object {
        const val SCHEME = "https"
        const val BASE_PATH = "gist.githubusercontent.com"
        const val PRODUCT_PATH =
            "r2vq/2ac197145db3f6cdf1a353feb744cf8e/raw/b1e722f608b00ddde138a0eef2261c6ffc8b08d7/cart.json"
        val BASE_URL = HttpUrl.Builder()
            .scheme(SCHEME)
            .host(BASE_PATH).build()
    }

    fun getItems(): RepoResult<Array<Product>>? {
        val url: HttpUrl = BASE_URL
            .newBuilder()
            .addPathSegments(PRODUCT_PATH)
            .build()

        val request = Request.Builder().url(url).tag("cart-request").build()
        try {
            mNetworkClient
                .newCall(request)
                .execute().use { response ->
                    if (response.isSuccessful) {
                        response.body?.let { body ->
                            val cartResponse = mParser.parseProducts(body.string())
                            return RepoResult(RepoResult.Status.SUCCESS, obj = cartResponse.entries)
                        }
                    } else {
                        return RepoResult(
                            RepoResult.Status.ERROR,
                            error = "Unexpected response from server $response"
                        )
                    }
                }

        } catch (exception: Exception) {
            return RepoResult(
                RepoResult.Status.ERROR,
                error = "Unexpected execption ${exception.message}"
            )
        }
        return null
    }

    fun cancelAllRequests() {
        mNetworkClient.dispatcher.cancelAll()
    }

    fun cancelRequest(tag: String) {
        for (call: Call in mNetworkClient.dispatcher.queuedCalls()) {
            call.request().tag()?.let {
                if (it == tag) {
                    call.cancel()
                }
            }
        }

        for (call: Call in mNetworkClient.dispatcher.runningCalls()) {
            call.request().tag()?.let {
                if (it == tag) {
                    call.cancel()
                }
            }
        }
    }
}