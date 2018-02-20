package com.bobowiec.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class NewsSources(
  @SerializedName("status")  var status: String = "",
  @SerializedName("sources") var sources: List<NewsSource> = emptyList()
)