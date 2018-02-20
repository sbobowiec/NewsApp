package com.bobowiec.newsapp.injection.module

import android.content.Context
import com.bobowiec.newsapp.util.AppSchedulerProvider
import com.bobowiec.newsapp.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

  @Provides
  @Singleton
  fun providesAppContext(): Context = context.applicationContext

  @Provides
  @Singleton
  fun provideSchedulerProvider() : SchedulerProvider = AppSchedulerProvider()

}