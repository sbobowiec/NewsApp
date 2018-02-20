package com.bobowiec.newsapp.ui.articles

import com.bobowiec.newsapp.data.model.NewsArticle
import com.bobowiec.newsapp.ui.base.MvpView

interface ArticlesView : MvpView{

  fun addArticles(articles: List<NewsArticle>)

}