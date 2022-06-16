package com.varsha.newsapp.data.remote

import com.varsha.newsapp.model.LoginResponse
import com.varsha.newsapp.model.NewsResponse
import com.varsha.newsapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {

    @GET(Constants.LOGIN_END_POINT)
    suspend fun loginUser(): Response<LoginResponse>

    @GET(Constants.DashBoard_END_POINT)
    suspend fun getAllNews(): Response<NewsResponse>

}