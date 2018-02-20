package com.bobowiec.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class NewsArticles(
  @SerializedName("status")       var status: String = "",
  @SerializedName("totalResults") var totalResults: Int = 0,
  @SerializedName("articles")     var articles: List<NewsArticle> = emptyList()
)