package com.example.pagingapp.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.pagingapp.model.Characters
import com.example.pagingapp.service.NetworkService
import io.reactivex.disposables.CompositeDisposable

class CharacterDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService
) : DataSource.Factory<Int, Characters>() {

    val mDataSourceLiveData = MutableLiveData<CharacterDataSource>()

    override fun create(): DataSource<Int, Characters> {
        val newsDataSource = CharacterDataSource(networkService, compositeDisposable)
        mDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}