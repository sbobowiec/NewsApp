package com.bobowiec.newsapp.data.model

import com.bobowiec.newsapp.ui.common.adapter.AdapterConstants
import com.bobowiec.newsapp.ui.common.adapter.ViewType
import com.google.gson.annotations.SerializedName

data class NewsSource(
  @SerializedName("id")           var id: String = "",
  @SerializedName("name")         var name: String = "",
  @SerializedName("description")  var description: String = "",
  @SerializedName("url")          var url: String = "",
  @SerializedName("category")     var category: String = "",
  @SerializedName("language")     var language: String = "",
  @SerializedName("country")      var country: String = ""
) : ViewType {

  override fun getViewType() = AdapterConstants.PUBLISHERS

}