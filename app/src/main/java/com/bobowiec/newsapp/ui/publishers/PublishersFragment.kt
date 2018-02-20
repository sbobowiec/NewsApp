package com.bobowiec.newsapp.ui.publishers

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bobowiec.newsapp.R
import com.bobowiec.newsapp.data.model.NewsSource
import com.bobowiec.newsapp.ui.base.BaseFragment
import com.bobowiec.newsapp.extensions.getAppComponent
import com.bobowiec.newsapp.extensions.hide
import com.bobowiec.newsapp.extensions.show
import com.bobowiec.newsapp.ui.publishers.adapter.PublishersAdapter
import kotlinx.android.synthetic.main.fragment_publishers.*
import javax.inject.Inject

class PublishersFragment : BaseFragment(), PublishersView {

  companion object {
    const val TAG = "PublishersFragment"

    fun create() = PublishersFragment()
  }

  @Inject
  lateinit var presenter: PublishersPresenter

  private val publishersAdapter = PublishersAdapter()

  override fun getTitle(): Int = R.string.title_fragment_publishers

  override fun getFragmentTag(): String = TAG

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    context.getAppComponent().inject(this)
    presenter.bindView(this)
  }

  override fun onDestroy() {
    presenter.unbindView()
    super.onDestroy()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_publishers, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupRecycler()
    presenter.requestPublishers()
  }

  override fun addPublishers(publishers: List<NewsSource>) {
    publishersAdapter.addPublishers(publishers)
  }

  override fun showProgress() {
    progress.show()
  }

  override fun hideProgress() {
    progress.hide()
  }

  private fun setupRecycler() {
    publishers.apply {
      val llManager = LinearLayoutManager(context)
      layoutManager = llManager

      val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
      addItemDecoration(divider)

      adapter = publishersAdapter
    }
  }

}