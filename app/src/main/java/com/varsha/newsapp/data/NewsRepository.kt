package com.varsha.newsapp.data

import com.varsha.newsapp.data.remote.NewsDataSource
import com.varsha.newsapp.model.LoginResponse
import com.varsha.newsapp.model.NewsResponse
import com.varsha.newsapp.util.BaseApiResponse
import com.varsha.newsapp.util.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class NewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) : BaseApiResponse() {

    suspend fun loginUser(): Flow<NetworkResult<LoginResponse>> {
        return flow {
            emit(safeApiCall { newsDataSource.loginUser() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllNews(): Flow<NetworkResult<NewsResponse>> {
        return flow {
            emit(safeApiCall { newsDataSource.getAllNews() })
        }.flowOn(Dispatchers.IO)
    }

}
