package com.bobowiec.newsapp.util

import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider : SchedulerProvider {

  val testScheduler: TestScheduler = TestScheduler()

  override fun uiScheduler() = testScheduler

  override fun ioScheduler() = testScheduler

}