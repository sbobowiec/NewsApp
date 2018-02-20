package com.bobowiec.newsapp.data.manager

import android.content.Context
import android.content.SharedPreferences
import com.bobowiec.newsapp.data.model.NewsArticle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import java.util.ArrayList

class DataManagerImpl(
    val context: Context,
    val gson: Gson
) : DataManager {

  companion object {
    private const val PREFERENCES_FILE_NAME = "com.bobowiec.newsapp.PREFERENCES"
    private const val KEY_SAVED_ARTICLES = "key_saved_articles"
  }

  private var preferences: SharedPreferences

  init {
    preferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
  }

  override fun saveArticle(article: NewsArticle) {
    val savedArticles = getArticlesFromPrefs()
    savedArticles.add(article)
    saveArticlesToPrefs(savedArticles)
  }

  override fun removeArticle(article: NewsArticle) {
    val filteredArticles = getArticlesFromPrefs().filterNot { article.title == it.title }
    saveArticlesToPrefs(filteredArticles)
  }

  override fun containsArticle(article: NewsArticle) =
      getArticlesFromPrefs().any { it.title == article.title }

  override fun getArticles(): Observable<List<NewsArticle>> = Observable.just(getArticlesFromPrefs())

  private fun getArticlesFromPrefs(): MutableList<NewsArticle> {
    val newsJSON = preferences.getString(KEY_SAVED_ARTICLES, null) ?: return ArrayList()
    val newsArticleType = object : TypeToken<List<NewsArticle>>() {}.type
    return gson.fromJson<List<NewsArticle>>(newsJSON, newsArticleType).toMutableList()
  }

  private fun saveArticlesToPrefs(articles: List<NewsArticle>) {
    preferences.edit().putString(KEY_SAVED_ARTICLES, gson.toJson(articles)).apply()
  }

}