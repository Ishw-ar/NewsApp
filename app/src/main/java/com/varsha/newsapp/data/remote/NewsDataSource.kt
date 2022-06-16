package com.varsha.newsapp.data.remote

import javax.inject.Inject

class NewsDataSource @Inject constructor(private val newsService: NewsService) {

    suspend fun loginUser() = newsService.loginUser()

    suspend fun getAllNews() = newsService.getAllNews()

}