package com.bobowiec.newsapp.ui.articles.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bobowiec.newsapp.data.model.NewsArticle
import com.bobowiec.newsapp.ui.common.adapter.AdapterConstants
import com.bobowiec.newsapp.ui.common.adapter.LoadingDelegateAdapter
import com.bobowiec.newsapp.ui.common.adapter.ViewType
import com.bobowiec.newsapp.ui.common.adapter.ViewTypeDelegateAdapter

class ArticlesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private var items: MutableList<ViewType> = arrayListOf()
  private var onArticleClickListener: OnArticleClickListener = {}
  private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

  private val loadingItem = object : ViewType {
    override fun getViewType() = AdapterConstants.LOADING
  }

  init {
    delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
    delegateAdapters.put(AdapterConstants.ARTICLES, ArticlesDelegateAdapter())
    items.add(loadingItem)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    delegateAdapters.get(viewType).onCreateViewHolder(parent)

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val viewType = getItemViewType(position)
    val delegateAdapter: ViewTypeDelegateAdapter = delegateAdapters.get(viewType)

    if (delegateAdapter is ArticlesDelegateAdapter) {
      delegateAdapter.setOnArticleClickListener(onArticleClickListener)
    }
    delegateAdapter.onBindViewHolder(holder, items[position])
  }

  override fun getItemCount() = items.count()

  override fun getItemViewType(position: Int) = items[position].getViewType()

  fun addArticles(articles: List<NewsArticle>) {
    val initPosition = items.size - 1

    items.removeAt(initPosition)          // remove loading
    notifyItemRemoved(initPosition)

    if (articles.isNotEmpty()) {
      items.addAll(articles)              // add items
      items.add(loadingItem)              // add loading again
      notifyItemRangeInserted(initPosition, items.size)
    }
  }

  fun setOnArticleClickListener(listener: OnArticleClickListener) {
    onArticleClickListener = listener
  }

  fun getArticles(): List<NewsArticle> {
    return items
        .filter { it.getViewType() == AdapterConstants.ARTICLES }
        .map { it as NewsArticle }
  }

}