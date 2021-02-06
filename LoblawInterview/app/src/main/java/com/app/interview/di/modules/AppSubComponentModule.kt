package com.app.interview.di.modules

import com.app.interview.cart.di.CartSubComponent
import com.app.interview.details.di.DetailsSubComponent
import com.app.interview.main.di.MainActivitySubComponent
import dagger.Module

@Module(
    subcomponents = [MainActivitySubComponent::class,
        CartSubComponent::class, DetailsSubComponent::class]
)
interface AppSubComponentModule