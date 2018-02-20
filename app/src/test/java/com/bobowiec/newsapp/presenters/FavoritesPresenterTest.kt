package com.bobowiec.newsapp.presenters

import com.bobowiec.newsapp.data.manager.DataManager
import com.bobowiec.newsapp.data.model.NewsArticle
import com.bobowiec.newsapp.ui.favorites.FavoritesPresenter
import com.bobowiec.newsapp.ui.favorites.FavoritesView
import com.bobowiec.newsapp.util.TestSchedulerProvider
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class FavoritesPresenterTest {

  @Mock
  private lateinit var dataManager: DataManager

  @Mock
  private lateinit var view: FavoritesView

  private lateinit var testSchedulerProvider: TestSchedulerProvider

  private lateinit var SUT: FavoritesPresenter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
    testSchedulerProvider = TestSchedulerProvider()
    SUT = FavoritesPresenter(testSchedulerProvider, dataManager)
    SUT.bindView(view)
  }

  @After
  fun teardown() {
    SUT.unbindView()
  }

  @Test
  fun load_favorite_articles() {
    // Given
    val favoriteArticles = Observable.create<List<NewsArticle>> { emitter ->
      emitter.onNext(favoriteArticles())
      emitter.onComplete()
    }

    // When
    `when`(dataManager.getArticles()).thenReturn(favoriteArticles)

    SUT.loadFavoriteArticles()

    testSchedulerProvider.testScheduler.triggerActions()

    // Then
    verify(view).addArticles(favoriteArticles())
  }

  @Test
  fun show_no_articles_view() {
    // Given
    val favoriteArticles = Observable.create<List<NewsArticle>> { emitter ->
      emitter.onNext(emptyList())
      emitter.onComplete()
    }

    // When
    `when`(dataManager.getArticles()).thenReturn(favoriteArticles)

    SUT.loadFavoriteArticles()

    testSchedulerProvider.testScheduler.triggerActions()

    // Then
    verify(view).showNoArticlesView()
  }

  private fun favoriteArticles() = listOf(
      NewsArticle(
          author = "A.A",
          title = "Test1",
          description = "Sample desc",
          url="https://onet.pl",
          urlToImage = null,
          publishedAt = null
      ),
      NewsArticle(
          author = "B.B",
          title = "Test2",
          description = "Sample desc",
          url="https://onet.pl",
          urlToImage = null,
          publishedAt = null
      ),
      NewsArticle(
          author = "C.C",
          title = "Test3",
          description = "Sample desc",
          url="https://onet.pl",
          urlToImage = null,
          publishedAt = null
      )
  )

}