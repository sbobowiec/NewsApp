package com.bobowiec.newsapp.ui.favorites

import com.bobowiec.newsapp.data.manager.DataManager
import com.bobowiec.newsapp.ui.base.BasePresenter
import com.bobowiec.newsapp.util.SchedulerProvider
import javax.inject.Inject

class FavoritesPresenter @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val dataManager: DataManager
) : BasePresenter<FavoritesView>() {

  fun loadFavoriteArticles() {
    dataManager.getArticles()
        .subscribeOn(schedulerProvider.ioScheduler())
        .observeOn(schedulerProvider.uiScheduler())
        .doOnSubscribe { view?.clearArticles() }
        .subscribe {
          if (it.isEmpty()) {
            view?.showNoArticlesView()
          } else {
            view?.addArticles(it)
          }
        }
  }

}