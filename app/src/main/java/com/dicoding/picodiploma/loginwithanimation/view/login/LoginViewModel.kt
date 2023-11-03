package com.dicoding.picodiploma.loginwithanimation.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.loginwithanimation.data.UserRepository
import com.dicoding.picodiploma.loginwithanimation.data.response.LoginResponse
import com.dicoding.picodiploma.loginwithanimation.data.Result


class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    private val _loginResponse = MediatorLiveData<Result<LoginResponse>>()
    val loginResponse: LiveData<Result<LoginResponse>> = _loginResponse

    fun login(email: String, password: String) {
        val liveData = repository.login(email, password)
        _loginResponse.addSource(liveData) { result ->
            _loginResponse.value = result
        }
    }
}