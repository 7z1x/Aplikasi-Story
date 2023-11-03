package com.dicoding.picodiploma.loginwithanimation.view.setting


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.loginwithanimation.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingViewModel(private val repository: UserRepository): ViewModel() {
    fun getLocale() = repository.getLocale().asLiveData(Dispatchers.IO)

    fun saveLocale(localeName: String) {
        viewModelScope.launch {
            repository.saveLocale(localeName)
        }
    }
}