package com.example.pagingapp.service

import com.example.pagingapp.model.CharacterData
import com.example.pagingapp.model.ResponseData
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("everything?q=sports&apiKey=aa67d8d98c8e4ad1b4f16dbd5f3be348")
    fun getArticles(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Single<ResponseData>

    @GET("character")
    fun getDataFromAPI(@Query("page") query: Int): Single<CharacterData>

    companion object {
        private const val baseURL = "https://rickandmortyapi.com/api/"  //"https://newsapi.org/v2/"

        fun getService(): NetworkService {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(NetworkService::class.java)
        }
    }
}