package com.example.pagingapp.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.pagingapp.model.News
import com.example.pagingapp.service.NetworkService
import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService)
    : DataSource.Factory<Int, News>() {

    val mDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, News> {
        val newsDataSource = NewsDataSource(networkService, compositeDisposable)
        mDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}