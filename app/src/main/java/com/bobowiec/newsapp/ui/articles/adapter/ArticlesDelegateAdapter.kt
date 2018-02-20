package com.bobowiec.newsapp.ui.articles.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.ViewGroup
import com.bobowiec.newsapp.R
import com.bobowiec.newsapp.data.model.NewsArticle
import com.bobowiec.newsapp.extensions.inflate
import com.bobowiec.newsapp.ui.common.adapter.ViewType
import com.bobowiec.newsapp.ui.common.adapter.ViewTypeDelegateAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_articles_item.view.*

typealias OnArticleClickListener = (NewsArticle) -> Unit

class ArticlesDelegateAdapter : ViewTypeDelegateAdapter {

  private var onArticleClickListener: OnArticleClickListener = {}

  override fun onCreateViewHolder(parent: ViewGroup) = ArticlesViewHolder(parent)

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    holder as ArticlesViewHolder
    holder.bind(item as NewsArticle)
  }

  fun setOnArticleClickListener(listener: OnArticleClickListener) {
    onArticleClickListener = listener
  }

  inner class ArticlesViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
      parent.inflate(R.layout.view_articles_item)) {

    fun bind(item: NewsArticle) = with(itemView) {
      if (TextUtils.isEmpty(item.urlToImage)) {
        Picasso.with(context)
            .load(R.drawable.ic_news_placeholder)
            .into(img_thumbnail)
      } else {
        Picasso.with(context)
            .load(item.urlToImage)
            .resize(80 ,80)
            .centerCrop()
            .placeholder(R.drawable.ic_news_placeholder)
            .error(R.drawable.ic_news_placeholder)
            .into(img_thumbnail)
      }

      title.text = item.title
      author.text = item.author

      setOnClickListener { onArticleClickListener.invoke(item) }
    }

  }

}