package com.example.dell.cinesky.injection.component

import com.example.dell.cinesky.injection.module.NetworkModule
import com.example.dell.cinesky.viewmodel.HomeViewModel
import com.example.dell.cinesky.viewmodel.MovieViewModel
import dagger.Component

import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param homeViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(homeViewModel: HomeViewModel)
    /**
     * Injects required dependencies into the specified PostViewModel.
     * @param movieViewModel PostViewModel in which to inject the dependencies
     */
    fun inject(movieViewModel: MovieViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}