package com.bobowiec.newsapp.injection.module

import android.content.Context
import com.bobowiec.newsapp.data.manager.DataManager
import com.bobowiec.newsapp.data.manager.DataManagerImpl
import com.bobowiec.newsapp.data.remote.NewsService
import com.bobowiec.newsapp.data.remote.NewsServiceImpl
import com.bobowiec.newsapp.data.remote.api.NewsApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

  @Provides
  @Singleton
  fun provideNewsService(api: NewsApi): NewsService = NewsServiceImpl(api)

  @Provides
  @Singleton
  fun provideDataManager(context: Context, gson: Gson): DataManager = DataManagerImpl(context, gson)

}