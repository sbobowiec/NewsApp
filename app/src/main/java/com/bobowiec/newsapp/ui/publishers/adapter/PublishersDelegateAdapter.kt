package com.bobowiec.newsapp.ui.publishers.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bobowiec.newsapp.R
import com.bobowiec.newsapp.data.model.NewsSource
import com.bobowiec.newsapp.extensions.inflate
import com.bobowiec.newsapp.ui.common.adapter.ViewType
import com.bobowiec.newsapp.ui.common.adapter.ViewTypeDelegateAdapter
import kotlinx.android.synthetic.main.view_publishers_item.view.*

class PublishersDelegateAdapter : ViewTypeDelegateAdapter {

  override fun onCreateViewHolder(parent: ViewGroup) = PublishersViewHolder(parent)

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    holder as PublishersViewHolder
    holder.bind(item as NewsSource)
  }

  inner class PublishersViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
      parent.inflate(R.layout.view_publishers_item)) {

    fun bind(item: NewsSource) = with(itemView) {
      name.text = item.name
      description.text = item.description
      url.text = item.url
    }

  }

}