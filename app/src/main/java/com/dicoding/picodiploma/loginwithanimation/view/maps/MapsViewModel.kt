package com.dicoding.picodiploma.loginwithanimation.view.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.loginwithanimation.data.Result
import com.dicoding.picodiploma.loginwithanimation.data.UserModel
import com.dicoding.picodiploma.loginwithanimation.data.UserRepository
import com.dicoding.picodiploma.loginwithanimation.data.response.ListStoryItem

class MapsViewModel(private val repository: UserRepository): ViewModel() {
    private val _storyWithLocation = MediatorLiveData<Result<List<ListStoryItem>>>()
    val storyListWithLocation: LiveData<Result<List<ListStoryItem>>> = _storyWithLocation

    fun getStoriesWithLocation(token: String) {
        val liveData = repository.getStoriesWithLocation(token)
        _storyWithLocation.addSource(liveData) { result ->
            _storyWithLocation.value = result
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}