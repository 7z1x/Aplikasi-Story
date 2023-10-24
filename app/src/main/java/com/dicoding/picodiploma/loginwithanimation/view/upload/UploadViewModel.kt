package com.dicoding.picodiploma.loginwithanimation.view.upload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.loginwithanimation.data.UserModel
import com.dicoding.picodiploma.loginwithanimation.data.UserRepository
import com.dicoding.picodiploma.loginwithanimation.data.response.UploadStoryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import com.dicoding.picodiploma.loginwithanimation.data.Result

class UploadViewModel(private val repository: UserRepository): ViewModel() {

    private val _uploadStoryResponse = MediatorLiveData<Result<UploadStoryResponse>>()
    val uploadStoryResponse: LiveData<Result<UploadStoryResponse>> = _uploadStoryResponse

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun uploadStory(token: String, file: MultipartBody.Part, description: RequestBody) {
        val liveData = repository.uploadStory(token, file, description)
        _uploadStoryResponse.addSource(liveData) { result ->
            _uploadStoryResponse.value = result
        }
    }
}