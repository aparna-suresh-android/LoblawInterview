package com.app.interview.cart.di

import com.app.interview.cart.CartFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [GenericViewModelFactoryModule::class,
        CartViewModelModule::class]
)
interface CartSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CartSubComponent
    }

    fun inject(cartFragment: CartFragment)
}