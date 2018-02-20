package com.bobowiec.newsapp.data.remote.api

import com.bobowiec.newsapp.data.model.NewsArticles
import com.bobowiec.newsapp.data.model.NewsSources
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

  @GET("top-headlines")
  fun getArticles(
    @Query("country")   country: String,
    @Query("page")      page: Int,
    @Query("pageSize")  pageSize: Int,
    @Query("apiKey")    apiKey: String): Observable<NewsArticles>

  @GET("sources")
  fun getSources(@Query("apiKey") apiKey: String): Observable<NewsSources>

}