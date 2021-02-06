package com.app.interview.details.di

import android.os.Bundle
import androidx.lifecycle.ViewModel

interface IAssistedViewModelFactory<T : ViewModel> {
    fun create(args: Bundle? = null): T
}