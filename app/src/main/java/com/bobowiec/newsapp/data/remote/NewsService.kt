package com.bobowiec.newsapp.data.remote

import com.bobowiec.newsapp.data.model.NewsArticles
import com.bobowiec.newsapp.data.model.NewsSources
import io.reactivex.Observable

interface NewsService {

  fun fetchArticles(page: Int): Observable<NewsArticles>

  fun fetchSources(page: Int): Observable<NewsSources>

}