package com.app.interview

import android.app.Application
import com.app.interview.di.AppComponent
import com.app.interview.di.DaggerAppComponent

class LoblawInterviewApplication : Application() {
    lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.create()
    }


}