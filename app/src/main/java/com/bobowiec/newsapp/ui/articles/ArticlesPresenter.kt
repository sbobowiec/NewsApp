package com.bobowiec.newsapp.ui.articles

import com.bobowiec.newsapp.data.remote.NewsService
import com.bobowiec.newsapp.ui.base.BasePresenter
import com.bobowiec.newsapp.util.SchedulerProvider
import javax.inject.Inject

class ArticlesPresenter @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val newsService: NewsService
) : BasePresenter<ArticlesView>() {

  private var page = 1

  fun requestArticles() {
    newsService.fetchArticles(page++)
        .map { it.articles }
        .subscribeOn(schedulerProvider.ioScheduler())
        .observeOn(schedulerProvider.uiScheduler())
        .subscribe { view?.addArticles(it) }
  }

}