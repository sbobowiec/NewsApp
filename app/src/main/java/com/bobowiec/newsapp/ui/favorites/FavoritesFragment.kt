package com.bobowiec.newsapp.ui.favorites

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bobowiec.newsapp.R
import com.bobowiec.newsapp.data.model.NewsArticle
import com.bobowiec.newsapp.ui.base.BaseFragment
import com.bobowiec.newsapp.extensions.getAppComponent
import com.bobowiec.newsapp.extensions.hide
import com.bobowiec.newsapp.extensions.show
import com.bobowiec.newsapp.ui.articles.details.ArticleDetailsActivity
import com.bobowiec.newsapp.ui.favorites.adapter.FavoritesAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*
import javax.inject.Inject

class FavoritesFragment : BaseFragment(), FavoritesView {

  companion object {
    const val TAG = "FavoritesFragment"

    const val ACTION_SAVED_ARTICLES_CHANGED = "com.bobowiec.newsapp.ACTION_SAVED_ARTICLES_CHANGED"

    fun create() = FavoritesFragment()
  }

  @Inject
  lateinit var presenter: FavoritesPresenter

  private val articlesAdapter = FavoritesAdapter()

  override fun getTitle(): Int = R.string.title_fragment_favorites

  override fun getFragmentTag(): String = TAG

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    context.getAppComponent().inject(this)
    presenter.bindView(this)
    LocalBroadcastManager.getInstance(context)
        .registerReceiver(refreshArticlesBroadcastReceiver, IntentFilter(ACTION_SAVED_ARTICLES_CHANGED))
  }

  override fun onDestroy() {
    presenter.unbindView()
    LocalBroadcastManager.getInstance(context).unregisterReceiver(refreshArticlesBroadcastReceiver)
    super.onDestroy()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_favorites, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupRecycler()
    setupListeners()
    presenter.loadFavoriteArticles()
  }

  override fun addArticles(articles: List<NewsArticle>) {
    no_saved_articles.hide()
    articlesAdapter.addFavoriteArticles(articles)
  }

  override fun clearArticles() {
    articlesAdapter.clear()
  }

  override fun showNoArticlesView() {
    no_saved_articles.show()
  }

  private fun setupRecycler() {
    saved_articles.apply {
      adapter = articlesAdapter

      val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
      addItemDecoration(divider)

      val linearLayoutManager = LinearLayoutManager(context)
      layoutManager = linearLayoutManager
      itemAnimator = null
    }
  }

  private fun setupListeners() {
    articlesAdapter.setOnArticleClickListener {
      startActivity(ArticleDetailsActivity.createIntent(context, it))
    }
  }

  private val refreshArticlesBroadcastReceiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
      if (intent?.action == ACTION_SAVED_ARTICLES_CHANGED) {
        presenter.loadFavoriteArticles()
      }
    }
  }

}