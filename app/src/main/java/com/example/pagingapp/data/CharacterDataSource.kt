package com.example.pagingapp.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.pagingapp.model.Characters
import com.example.pagingapp.model.State
import com.example.pagingapp.service.NetworkService
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class CharacterDataSource(
    private val networkService: NetworkService,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Characters>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Characters>
    ) {
        updateState(State.LOADING)
        compositeDisposable.add(
            networkService.getDataFromAPI(1)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        callback.onResult(
                            response.results,
                            null,
                            2
                        )
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Characters>
    ) {
        updateState(State.LOADING)
        compositeDisposable.add(
            networkService.getDataFromAPI(params.key)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        callback.onResult(
                            response.results,
                            params.key + 1
                        )
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Characters>
    ) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}