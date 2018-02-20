package com.bobowiec.newsapp.ui.articles

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bobowiec.newsapp.R
import com.bobowiec.newsapp.data.model.NewsArticle
import com.bobowiec.newsapp.ui.base.BaseFragment
import com.bobowiec.newsapp.extensions.getAppComponent
import com.bobowiec.newsapp.ui.articles.adapter.ArticlesAdapter
import com.bobowiec.newsapp.ui.articles.details.ArticleDetailsActivity
import com.bobowiec.newsapp.ui.common.scroll.InfiniteScrollListener
import kotlinx.android.synthetic.main.fragment_articles.*
import javax.inject.Inject

class ArticlesFragment : BaseFragment(), ArticlesView {

  companion object {
    const val TAG = "ArticlesFragment"

    fun create() = ArticlesFragment()
  }

  @Inject
  lateinit var presenter: ArticlesPresenter

  private val articlesAdapter = ArticlesAdapter()

  override fun getTitle(): Int = R.string.title_fragment_articles

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
    return inflater.inflate(R.layout.fragment_articles, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupRecycler()
    setupListeners()
  }

  override fun onResume() {
    super.onResume()

    if (articlesAdapter.getArticles().isEmpty()) {
      presenter.requestArticles()
    }
  }

  override fun addArticles(articles: List<NewsArticle>) {
    articlesAdapter.addArticles(articles)
  }

  private fun setupRecycler() {
    articles.apply {
      adapter = articlesAdapter

      val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
      addItemDecoration(divider)

      val linearLayoutManager = LinearLayoutManager(context)
      layoutManager = linearLayoutManager
      itemAnimator = null

      addOnScrollListener(InfiniteScrollListener({ presenter.requestArticles() }, linearLayoutManager))
    }
  }

  private fun setupListeners() {
    articlesAdapter.setOnArticleClickListener {
      startActivity(ArticleDetailsActivity.createIntent(context, it))
    }
  }

}