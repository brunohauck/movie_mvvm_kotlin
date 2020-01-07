package com.example.dell.cinesky.base

import android.arch.lifecycle.ViewModel
import com.example.dell.cinesky.injection.component.ViewModelInjector
import com.example.dell.cinesky.injection.module.NetworkModule
import com.example.dell.cinesky.viewmodel.HomeViewModel
import com.example.dell.cinesky.viewmodel.MovieViewModel
import com.example.dell.cinesky.injection.component.DaggerViewModelInjector

abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is HomeViewModel -> injector.inject(this)
            is MovieViewModel -> injector.inject(this)
        }
    }
}