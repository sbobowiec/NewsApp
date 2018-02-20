package com.bobowiec.newsapp.ui.publishers.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bobowiec.newsapp.data.model.NewsSource
import com.bobowiec.newsapp.ui.common.adapter.AdapterConstants
import com.bobowiec.newsapp.ui.common.adapter.ViewType
import com.bobowiec.newsapp.ui.common.adapter.ViewTypeDelegateAdapter

class PublishersAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private var items: MutableList<ViewType> = arrayListOf()
  private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

  init {
    delegateAdapters.put(AdapterConstants.PUBLISHERS, PublishersDelegateAdapter())
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
      delegateAdapters.get(viewType).onCreateViewHolder(parent)

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
      delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, items[position])

  override fun getItemCount() = items.size

  override fun getItemViewType(position: Int) = items[position].getViewType()

  fun addPublishers(publishers: List<NewsSource>) {
    val initPosition = if (items.isEmpty()) 0 else items.size - 1
    items.addAll(publishers)
    notifyItemRangeInserted(initPosition, items.size)
  }

}