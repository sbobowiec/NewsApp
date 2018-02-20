package com.bobowiec.newsapp.ui.publishers

import com.bobowiec.newsapp.data.remote.NewsService
import com.bobowiec.newsapp.ui.base.BasePresenter
import com.bobowiec.newsapp.util.SchedulerProvider
import javax.inject.Inject

class PublishersPresenter @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val newsService: NewsService
) : BasePresenter<PublishersView>() {

  private var page = 1

  fun requestPublishers() {
    newsService.fetchSources(page++)
        .map { it.sources }
        .subscribeOn(schedulerProvider.ioScheduler())
        .observeOn(schedulerProvider.uiScheduler())
        .doOnSubscribe { view?.showProgress() }
        .doOnComplete { view?.hideProgress() }
        .subscribe { view?.addPublishers(it) }
  }

}