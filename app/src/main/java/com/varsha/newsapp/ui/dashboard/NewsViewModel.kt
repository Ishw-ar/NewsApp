package com.varsha.newsapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varsha.newsapp.data.NewsRepository
import com.varsha.newsapp.model.NewsResponse
import com.varsha.newsapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private val _newsResponse: MutableLiveData<NetworkResult<NewsResponse>> =
        MutableLiveData()
    val newsResponse: LiveData<NetworkResult<NewsResponse>> = _newsResponse

    fun getAllNews() = viewModelScope.launch {
        _newsResponse.value = NetworkResult.Loading()
        newsRepository.getAllNews()
            .collect {
                for (item in it.data?.articles!!) {
                    _newsResponse.value = it
                }
            }
    }
}