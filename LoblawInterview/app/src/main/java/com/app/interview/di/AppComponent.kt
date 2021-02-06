package com.app.interview.di

import com.app.interview.cart.di.CartSubComponent
import com.app.interview.details.di.DetailsSubComponent
import com.app.interview.di.modules.AppModule
import com.app.interview.di.modules.AppSubComponentModule
import com.app.interview.di.modules.NetworkModule
import com.app.interview.main.di.MainActivitySubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppSubComponentModule::class,
        AppModule::class,
        NetworkModule::class]
)
interface AppComponent {

    fun getMainActivitySubComponent(): MainActivitySubComponent.Factory
    fun getCartSubComponent(): CartSubComponent.Factory
    fun getDetailsSubComponent(): DetailsSubComponent.Factory

}