package com.bobowiec.newsapp.ui.favorites.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bobowiec.newsapp.data.model.NewsArticle
import com.bobowiec.newsapp.ui.articles.adapter.ArticlesDelegateAdapter
import com.bobowiec.newsapp.ui.articles.adapter.OnArticleClickListener
import com.bobowiec.newsapp.ui.common.adapter.AdapterConstants
import com.bobowiec.newsapp.ui.common.adapter.ViewType
import com.bobowiec.newsapp.ui.common.adapter.ViewTypeDelegateAdapter

class FavoritesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private var items: MutableList<ViewType> = arrayListOf()
  private var onArticleClickListener: OnArticleClickListener = {}
  private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

  init {
    delegateAdapters.put(AdapterConstants.ARTICLES, ArticlesDelegateAdapter())
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
      delegateAdapters.get(viewType).onCreateViewHolder(parent)

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val viewType = getItemViewType(position)
    val delegateAdapter: ViewTypeDelegateAdapter = delegateAdapters.get(viewType)

    (delegateAdapter as ArticlesDelegateAdapter).apply {
      delegateAdapter.setOnArticleClickListener(onArticleClickListener)
      delegateAdapter.onBindViewHolder(holder, items[position])
    }
  }

  override fun getItemCount() = items.count()

  override fun getItemViewType(position: Int) = items[position].getViewType()

  fun addFavoriteArticles(publishers: List<NewsArticle>) {
    val initPosition = if (items.isEmpty()) 0 else items.size - 1
    items.addAll(publishers)
    notifyItemRangeInserted(initPosition, items.size)
  }

  fun clear() {
    val count = items.count()
    items.clear()
    notifyItemRangeRemoved(0, count)
  }

  fun setOnArticleClickListener(listener: OnArticleClickListener) {
    onArticleClickListener = listener
  }

}