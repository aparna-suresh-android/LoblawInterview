package com.app.interview.repository

import com.app.interview.repository.entity.Product
import com.app.interview.repository.network.Network
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val mNetwork: Network,
    private val mBackgroundDispatcher: CoroutineDispatcher
) {
    suspend fun getProducts(): RepoResult<Array<Product>>? =
        withContext(mBackgroundDispatcher) {
            return@withContext mNetwork.getItems()
        }

    fun cancelAllRequests() {
        mNetwork.cancelAllRequests()
    }

    fun cancelRequest(tag: String) {
        mNetwork.cancelRequest(tag)
    }
}