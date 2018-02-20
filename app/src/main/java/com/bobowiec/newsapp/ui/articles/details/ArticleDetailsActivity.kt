package com.bobowiec.newsapp.ui.articles.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils.isEmpty
import android.view.Menu
import android.view.MenuItem
import com.bobowiec.newsapp.R
import com.bobowiec.newsapp.data.model.NewsArticle
import com.bobowiec.newsapp.extensions.getAppComponent
import com.bobowiec.newsapp.extensions.hide
import com.bobowiec.newsapp.extensions.show
import com.bobowiec.newsapp.ui.favorites.FavoritesFragment.Companion.ACTION_SAVED_ARTICLES_CHANGED
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_article_details.*
import javax.inject.Inject

class ArticleDetailsActivity : AppCompatActivity(), ArticleDetailsView {

  companion object {
    private const val EXTRA_ARTICLE = "extra_article"

    fun createIntent(context: Context, article: NewsArticle): Intent {
      return Intent(context, ArticleDetailsActivity::class.java).apply {
        putExtra(EXTRA_ARTICLE, article)
      }
    }
  }

  @Inject
  lateinit var presenter: ArticleDetailsPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_article_details)
    setupToolbar()
    getAppComponent().inject(this)
    presenter.bindView(this)

    val article = intent.getParcelableExtra<NewsArticle>(EXTRA_ARTICLE)
    presenter.setArticle(article)
    bind(article)
  }

  override fun onDestroy() {
    presenter.unbindView()
    super.onDestroy()
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_article_details, menu)

    val addToFavoritesItem = menu.findItem(R.id.menu_add_to_favorites)
    val removeFromFavoritesItem = menu.findItem(R.id.menu_remove_from_favorites)

    if (presenter.isArticleAddedToFavorites()) {
      addToFavoritesItem.hide()
      removeFromFavoritesItem.show()
    } else {
      addToFavoritesItem.show()
      removeFromFavoritesItem.hide()
    }

    return true
  }

  override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
    android.R.id.home -> {
      onBackPressed()
      true
    }
    R.id.menu_add_to_favorites -> {
      presenter.addArticleToFavorites()
      sendSavedArticlesChangedBroadcast()
      true
    }
    R.id.menu_remove_from_favorites -> {
      presenter.removeArticleFromFavorites()
      sendSavedArticlesChangedBroadcast()
      true
    }
    else -> super.onOptionsItemSelected(item)
  }

  override fun refreshMenu() {
    invalidateOptionsMenu()
  }

  private fun setupToolbar() {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  private fun bind(article: NewsArticle) {
    val imageUrl = article.urlToImage
    if (!isEmpty(imageUrl)) {
      Picasso.with(this)
          .load(imageUrl)
          .fit()
          .centerCrop()
          .into(article_image)
    } else {
      article_image.hide()
    }

    article_title.text = article.title
    article_description.text = article.description
    article_url.text = article.url
  }

  private fun sendSavedArticlesChangedBroadcast() {
    val intent = Intent(ACTION_SAVED_ARTICLES_CHANGED)
    LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
  }

}