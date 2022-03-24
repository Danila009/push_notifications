package com.example.cloudmessaging.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cloudmessaging.data.model.PushNotification
import com.example.cloudmessaging.data.repository.ApiRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val apiRepository: ApiRepository
):ViewModel() {

    fun poshFirebase(pushNotification: PushNotification){
        viewModelScope.launch {
            val response = apiRepository.pushFirebase(pushNotification)
            Log.e("Firebase", response.toString())
        }
    }
}