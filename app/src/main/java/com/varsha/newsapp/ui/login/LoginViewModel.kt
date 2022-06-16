package com.varsha.newsapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varsha.newsapp.data.NewsRepository
import com.varsha.newsapp.model.LoginResponse
import com.varsha.newsapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private val _loginResponse: MutableLiveData<NetworkResult<LoginResponse>> =
        MutableLiveData()
    val loginResponse: LiveData<NetworkResult<LoginResponse>> = _loginResponse

    fun loginUser() = viewModelScope.launch {
        _loginResponse.value = NetworkResult.Loading()
        newsRepository.loginUser()
            .collect {
                _loginResponse.value = it
            }
    }

}