package com.bobowiec.newsapp.injection.module

import com.bobowiec.newsapp.BuildConfig
import com.bobowiec.newsapp.data.remote.api.NewsApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Singleton
  fun provideGson() = Gson()

  @Provides
  @Singleton
  fun provideOkHttpClient() = OkHttpClient()

  @Provides
  @Singleton
  fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) : Retrofit =
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

  @Provides
  @Singleton
  fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

}