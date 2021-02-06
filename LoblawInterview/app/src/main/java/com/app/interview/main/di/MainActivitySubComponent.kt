package com.app.interview.main.di

import com.app.interview.main.MainActivity
import dagger.Subcomponent

@Subcomponent
interface MainActivitySubComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create() : MainActivitySubComponent;
    }

    fun inject(activity : MainActivity)
}