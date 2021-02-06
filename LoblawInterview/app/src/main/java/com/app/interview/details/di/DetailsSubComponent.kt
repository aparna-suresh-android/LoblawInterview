package com.app.interview.details.di

import com.app.interview.details.ProductDetailsFragment
import dagger.Subcomponent

@Subcomponent
interface DetailsSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailsSubComponent
    }

    fun inject(fragmentProduct: ProductDetailsFragment)
}