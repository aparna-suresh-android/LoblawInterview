package com.app.interview.cart.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

class GenericViewModelFactory @Inject constructor(
    private val viewModelMap:
    @JvmSuppressWildcards
    Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (viewModelMap[modelClass] != null) {
            return viewModelMap[modelClass]?.get() as T
        }
        throw IllegalArgumentException("Unknown model class $modelClass")
    }
}


@Module
interface GenericViewModelFactoryModule {
    @Binds
    fun getViewModelFactory(viewModelFactory: GenericViewModelFactory)
            : ViewModelProvider.Factory
}