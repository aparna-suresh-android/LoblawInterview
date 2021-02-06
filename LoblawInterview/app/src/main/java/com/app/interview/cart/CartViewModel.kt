package com.app.interview.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.interview.repository.RepoResult
import com.app.interview.repository.Repository
import com.app.interview.repository.entity.Product
import com.app.interview.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(private val mRepository: Repository)
    : ViewModel() {


    private var mIsNetworkConnected = false
    val mErrorText = MutableLiveData<String>()
    var mUiState = MutableLiveData(UiState.UNINITIALIZED)
    val productList = MutableLiveData<List<Product>>()

    private fun startLoadingCart() {
        mUiState.value = UiState.LOADING
        viewModelScope.launch {
            val repoResult: RepoResult<Array<Product>>? = mRepository.getProducts()
            repoResult?.let { result ->
                if (result.status == RepoResult.Status.SUCCESS) {
                    mUiState.value = UiState.SUCCESS
                    productList.value = result.obj!!.asList()

                } else if (result.status == RepoResult.Status.ERROR) {
                    mUiState.value = UiState.ERROR
                    mErrorText.value = result.error
                }
            }
        }
    }

    fun updateState(connected: Boolean) {
        if (mUiState.value == UiState.UNINITIALIZED) {
            mIsNetworkConnected = connected
            if (!mIsNetworkConnected) {
                setError("Network unavailable.Please try again!!!")
            } else {
                startLoadingCart()
            }
            return
        }


        if (mIsNetworkConnected != connected) {
            mIsNetworkConnected = connected
            if (!mIsNetworkConnected) {
                if (mUiState.value == UiState.LOADING) {
                    mRepository.cancelRequest("cart-request")
                    setError("Network unavailable.Please try again!!!")
                }
            } else {
                if (mUiState.value == UiState.ERROR) {
                    startLoadingCart()
                }

            }
        }
    }


    private fun setError(errorMsg: String) {
        mUiState.value = UiState.ERROR
        mErrorText.value = errorMsg
    }
}