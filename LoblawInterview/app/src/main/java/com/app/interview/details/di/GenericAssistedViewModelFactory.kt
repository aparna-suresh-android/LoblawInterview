package com.app.interview.details.di

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class GenericAssistedViewModelFactory<out V : ViewModel>
    (
    private val viewModelFactory: IAssistedViewModelFactory<V>,
    private val owner: SavedStateRegistryOwner,
    private val args: Bundle?,
) : AbstractSavedStateViewModelFactory(owner, args) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return viewModelFactory.create(args) as T
    }
}