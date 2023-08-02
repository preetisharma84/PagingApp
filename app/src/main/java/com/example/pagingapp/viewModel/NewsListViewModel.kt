package com.example.pagingapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagingapp.data.NewsDataSource
import com.example.pagingapp.data.NewsDataSourceFactory
import com.example.pagingapp.model.News
import com.example.pagingapp.model.State
import com.example.pagingapp.service.NetworkService
import io.reactivex.disposables.CompositeDisposable

class NewsListViewModel : ViewModel() {

    private val pageSize = 10
    private val networkService = NetworkService.getService()
    private val compositeDisposable = CompositeDisposable()
    private val mDataSourceFactory: NewsDataSourceFactory =
        NewsDataSourceFactory(compositeDisposable, networkService)
    var mDataList: LiveData<PagedList<News>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        mDataList = LivePagedListBuilder(mDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap(
        mDataSourceFactory.mDataSourceLiveData,
        NewsDataSource::state
    )

    fun retry() {
        mDataSourceFactory.mDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return mDataList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}