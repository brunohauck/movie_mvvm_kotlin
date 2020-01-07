package com.example.dell.cinesky.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.view.View
import com.example.dell.cinesky.R
import com.example.dell.cinesky.adapter.MovieAdapter
import com.example.dell.cinesky.base.BaseViewModel
import com.example.dell.cinesky.model.Movie
import com.example.dell.cinesky.model.MovieDao
import com.example.dell.cinesky.service.ApiMovies
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel(private val movieDao: MovieDao): BaseViewModel(){
    @Inject
    lateinit var movieApi: ApiMovies
    val movieAdapter: MovieAdapter = MovieAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    private lateinit var subscription: Disposable

    init{
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPosts(){
        subscription = Observable.fromCallable { movieDao.all }
                .concatMap {
                    dbMovieList ->
                    if(dbMovieList.isEmpty())
                        movieApi.getMovies().concatMap {
                            apiMovieList -> movieDao.insertAll(*apiMovieList.toTypedArray())
                            Observable.just(apiMovieList)
                        }
                    else
                        Observable.just(dbMovieList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveMovieListStart() }
                .doOnTerminate { onRetrieveMovieListFinish() }
                .subscribe(
                        { result -> onRetrieveMovieListSuccess(result) },
                        { onRetrieveMovieListError() }
                )
    }

    private fun onRetrieveMovieListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveMovieListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveMovieListSuccess(movieList:List<Movie>){
        movieAdapter.updateMovieList(movieList)
    }

    private fun onRetrieveMovieListError(){
        errorMessage.value = R.string.post_error
    }
}



