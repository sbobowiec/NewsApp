package com.bobowiec.newsapp.ui.articles.details

import com.bobowiec.newsapp.data.manager.DataManager
import com.bobowiec.newsapp.data.model.NewsArticle
import com.bobowiec.newsapp.ui.base.BasePresenter
import javax.inject.Inject

class ArticleDetailsPresenter @Inject constructor(
    private val dataManager: DataManager
) : BasePresenter<ArticleDetailsView>() {

  private lateinit var article: NewsArticle

  fun setArticle(article: NewsArticle) {
    this.article = article
  }

  fun addArticleToFavorites() {
    dataManager.saveArticle(article)
    view?.refreshMenu()
  }

  fun removeArticleFromFavorites() {
    dataManager.removeArticle(article)
    view?.refreshMenu()
  }

  fun isArticleAddedToFavorites() = dataManager.containsArticle(article)

}