package com.bobowiec.newsapp.data.model

import android.os.Parcel
import android.os.Parcelable
import com.bobowiec.newsapp.extensions.createParcel
import com.bobowiec.newsapp.ui.common.adapter.AdapterConstants
import com.bobowiec.newsapp.ui.common.adapter.ViewType
import com.google.gson.annotations.SerializedName

data class NewsArticle(
  @SerializedName("author")       var author: String? = "",
  @SerializedName("title")        var title: String = "",
  @SerializedName("description")  var description: String? = "",
  @SerializedName("url")          var url: String = "",
  @SerializedName("urlToImage")   var urlToImage: String? = "",
  @SerializedName("publishedAt")  var publishedAt: String? = ""
) : ViewType, Parcelable {

  companion object {
    @JvmField
    @Suppress("unused")
    val CREATOR = createParcel { NewsArticle(it) }
  }

  constructor(source: Parcel) : this(
      source.readString(),
      source.readString(),
      source.readString(),
      source.readString(),
      source.readString(),
      source.readString()
  )

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel?, flags: Int) {
    dest?.writeString(author)
    dest?.writeString(title)
    dest?.writeString(description)
    dest?.writeString(url)
    dest?.writeString(urlToImage)
    dest?.writeString(publishedAt)
  }

  override fun getViewType() = AdapterConstants.ARTICLES

}