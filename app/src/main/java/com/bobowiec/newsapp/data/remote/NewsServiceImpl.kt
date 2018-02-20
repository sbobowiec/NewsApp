package com.bobowiec.newsapp.data.remote

import com.bobowiec.newsapp.BuildConfig
import com.bobowiec.newsapp.data.remote.api.NewsApi

class NewsServiceImpl(private val newsApi: NewsApi): NewsService {

  companion object {
    private const val PAGE_SIZE = 20
    private const val API_KEY = BuildConfig.API_KEY
    private const val COUNTRY = "pl"
  }

  override fun fetchArticles(page: Int) = newsApi.getArticles(
      country = COUNTRY,
      page = page,
      pageSize = PAGE_SIZE,
      apiKey = API_KEY
  )

  override fun fetchSources(page: Int) = newsApi.getSources(apiKey = API_KEY)

}