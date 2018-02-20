package com.bobowiec.newsapp.ui.favorites

import com.bobowiec.newsapp.data.model.NewsArticle
import com.bobowiec.newsapp.ui.base.MvpView

interface FavoritesView : MvpView {

  fun clearArticles()

  fun addArticles(articles: List<NewsArticle>)

  fun showNoArticlesView()

}