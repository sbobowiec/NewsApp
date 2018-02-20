package com.bobowiec.newsapp.ui.publishers

import com.bobowiec.newsapp.data.model.NewsSource
import com.bobowiec.newsapp.ui.base.MvpView

interface PublishersView : MvpView {

  fun addPublishers(publishers: List<NewsSource>)

  fun showProgress()

  fun hideProgress()

}