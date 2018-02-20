package com.bobowiec.newsapp.ui.common.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bobowiec.newsapp.R
import com.bobowiec.newsapp.extensions.inflate

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

  override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = LoadingViewHolder(parent)

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) { }

  class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
      parent.inflate(R.layout.view_loading_item)
  )

}