package com.bobowiec.newsapp.presenters

import com.bobowiec.newsapp.data.model.NewsSource
import com.bobowiec.newsapp.data.model.NewsSources
import com.bobowiec.newsapp.data.remote.NewsService
import com.bobowiec.newsapp.ui.publishers.PublishersPresenter
import com.bobowiec.newsapp.ui.publishers.PublishersView
import com.bobowiec.newsapp.util.TestSchedulerProvider
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class PublishersPresenterTest {

  @Mock
  private lateinit var newsService: NewsService

  @Mock
  private lateinit var view: PublishersView

  private lateinit var testSchedulerProvider: TestSchedulerProvider

  private lateinit var SUT: PublishersPresenter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
    testSchedulerProvider = TestSchedulerProvider()
    SUT = PublishersPresenter(testSchedulerProvider, newsService)
    SUT.bindView(view)
  }

  @After
  fun teardown() {
    SUT.unbindView()
  }

  @Test
  fun request_publishers_from_api() {
    // Given
    val newsSources = Observable.create<NewsSources> { emitter ->
      emitter.onNext(newsSources())
      emitter.onComplete()
    }

    // When
    `when`(newsService.fetchSources(anyInt())).thenReturn(newsSources)

    SUT.requestPublishers()

    testSchedulerProvider.testScheduler.triggerActions()

    // Then
    verify(view).addPublishers(sourcesList())
  }

  private fun newsSources() = NewsSources("ok", sourcesList())

  private fun sourcesList() = listOf(
      NewsSource(
          id = "s1",
          name = "source1",
          description = "Sample desc",
          language = "pl",
          category = "all",
          country = "PL"
      ),
      NewsSource(
          id = "s2",
          name = "source2",
          description = "Sample desc",
          language = "pl",
          category = "all",
          country = "PL"
      ),
      NewsSource(
          id = "s3",
          name = "source3",
          description = "Sample desc",
          language = "pl",
          category = "all",
          country = "PL"
      )
  )

}