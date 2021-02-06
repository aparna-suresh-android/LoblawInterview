package com.app.interview.details

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.interview.details.di.IAssistedViewModelFactory
import com.app.interview.repository.entity.Product
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ProductDetailsViewModel @AssistedInject constructor(
    @Assisted
    private val args: Bundle?
) : ViewModel() {

    @AssistedFactory
    interface Factory : IAssistedViewModelFactory<ProductDetailsViewModel> {
        override fun create(args: Bundle?)
                : ProductDetailsViewModel
    }
    private var mProduct = MutableLiveData<Product>()
    val product: LiveData<Product> = mProduct
    init {
        mProduct.value = args!!.getParcelable("product")
    }
}