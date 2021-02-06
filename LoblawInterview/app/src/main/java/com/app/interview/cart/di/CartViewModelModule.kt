package com.app.interview.cart.di

import androidx.lifecycle.ViewModel
import com.app.interview.cart.CartViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CartViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    fun bindPopularTvViewModel(viewModel: CartViewModel): ViewModel
}