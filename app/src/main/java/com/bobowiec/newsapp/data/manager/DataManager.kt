package com.bobowiec.newsapp.data.manager

import com.bobowiec.newsapp.data.model.NewsArticle
import io.reactivex.Observable

interface DataManager {

  fun saveArticle(article: NewsArticle)

  fun removeArticle(article: NewsArticle)

  fun containsArticle(article: NewsArticle): Boolean

  fun getArticles(): Observable<List<NewsArticle>>

}