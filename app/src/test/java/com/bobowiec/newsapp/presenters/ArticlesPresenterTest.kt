package com.bobowiec.newsapp.presenters

import com.bobowiec.newsapp.data.model.NewsArticle
import com.bobowiec.newsapp.data.model.NewsArticles
import com.bobowiec.newsapp.data.remote.NewsService
import com.bobowiec.newsapp.ui.articles.ArticlesPresenter
import com.bobowiec.newsapp.ui.articles.ArticlesView
import com.bobowiec.newsapp.util.TestSchedulerProvider
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.*

class ArticlesPresenterTest {

  @Mock
  private lateinit var newsService: NewsService

  @Mock
  private lateinit var view: ArticlesView

  private lateinit var testSchedulerProvider: TestSchedulerProvider

  private lateinit var SUT: ArticlesPresenter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
    testSchedulerProvider = TestSchedulerProvider()
    SUT = ArticlesPresenter(testSchedulerProvider, newsService)
    SUT.bindView(view)
  }

  @After
  fun teardown() {
    SUT.unbindView()
  }

  @Test
  fun request_articles_from_api() {
    // Given
    val newsArticles = Observable.create<NewsArticles> { emitter ->
      emitter.onNext(newsArticles())
      emitter.onComplete()
    }

    // When
    `when`(newsService.fetchArticles(anyInt())).thenReturn(newsArticles)

    SUT.requestArticles()

    testSchedulerProvider.testScheduler.triggerActions()

    // Then
    verify(view).addArticles(articlesList())
  }

  private fun newsArticles() = NewsArticles("ok", 3, articlesList())

  private fun articlesList() = listOf(
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