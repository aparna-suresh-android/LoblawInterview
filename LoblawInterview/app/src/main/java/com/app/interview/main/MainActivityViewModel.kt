package com.app.interview.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.interview.repository.entity.Product
import com.app.interview.utils.Event

class MainActivityViewModel : ViewModel() {

    var clickedProduct = MutableLiveData<Event<Product>>()

    fun onProductClicked(item: Product) {
        clickedProduct.value = Event(item)
    }
}