package com.app.interview.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData

class ConnectivityChangeLiveData(private val context: Context) : LiveData<Boolean>() {

    private val connectivityChangeBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val connMgr = context?.let {
                it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            }
            val isNetworkConnected = (connMgr?.activeNetworkInfo?.isConnected) == true

            if (value != isNetworkConnected) {
                value = isNetworkConnected
            }
        }
    }


    override fun onActive() {
        super.onActive()
        context.registerReceiver(
            connectivityChangeBroadcastReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(connectivityChangeBroadcastReceiver)
    }

    companion object {
        private lateinit var mNetworkChange: ConnectivityChangeLiveData

        @MainThread
        fun getInstance(context: Context): ConnectivityChangeLiveData {
            if (Companion::mNetworkChange.isInitialized) {
                return mNetworkChange
            } else {
                mNetworkChange = ConnectivityChangeLiveData(context)
            }
            return mNetworkChange
        }

    }

}