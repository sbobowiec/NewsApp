package com.bobowiec.newsapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bobowiec.newsapp.R
import com.bobowiec.newsapp.extensions.show
import com.bobowiec.newsapp.ui.articles.ArticlesFragment
import com.bobowiec.newsapp.ui.base.BaseFragment
import com.bobowiec.newsapp.ui.favorites.FavoritesFragment
import com.bobowiec.newsapp.ui.publishers.PublishersFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setupToolbar()
    setupListeners()

    changeFragment(ArticlesFragment.create())
  }

  private fun setupToolbar() {
    setSupportActionBar(toolbar)
  }

  private fun setupListeners() {
    bottomNavigation.setOnNavigationItemReselectedListener { /* Empty listener to block reselection */ }
    bottomNavigation.setOnNavigationItemSelectedListener { onBottomMenuItemSelected(it) }
  }

  private fun onBottomMenuItemSelected(menuItem: MenuItem): Boolean {
    when (menuItem.itemId) {
      R.id.menu_item_articles -> {
        changeFragment(ArticlesFragment.create())
        toolbar.show()
      }
      R.id.menu_favorites -> {
        changeFragment(FavoritesFragment.create())
        toolbar.show()
      }
      R.id.menu_item_publishers -> {
        changeFragment(PublishersFragment.create())
        toolbar.show()
      }
      else -> return false
    }
    return true
  }

  private fun changeFragment(fragment: BaseFragment) {
    supportFragmentManager.beginTransaction().apply {
      replace(R.id.container, fragment, fragment.getFragmentTag())
    }.commit()
    title = getString(fragment.getTitle())
  }

}
