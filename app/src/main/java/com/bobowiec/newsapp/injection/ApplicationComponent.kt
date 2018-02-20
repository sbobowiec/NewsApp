package com.bobowiec.newsapp.injection

import com.bobowiec.newsapp.injection.module.ApplicationModule
import com.bobowiec.newsapp.injection.module.DataModule
import com.bobowiec.newsapp.injection.module.NetworkModule
import com.bobowiec.newsapp.ui.articles.ArticlesFragment
import com.bobowiec.newsapp.ui.articles.details.ArticleDetailsActivity
import com.bobowiec.newsapp.ui.favorites.FavoritesFragment
import com.bobowiec.newsapp.ui.publishers.PublishersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
  ApplicationModule::class,
  NetworkModule::class,
  DataModule::class
])
interface ApplicationComponent {

  fun inject(fragment: ArticlesFragment)

  fun inject(fragment: FavoritesFragment)

  fun inject(fragment: PublishersFragment)

  fun inject(activity: ArticleDetailsActivity)

}