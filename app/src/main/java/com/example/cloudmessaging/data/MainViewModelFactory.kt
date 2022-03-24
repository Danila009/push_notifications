package com.example.cloudmessaging.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cloudmessaging.data.repository.ApiRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val apiRepository: ApiRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(apiRepository) as T
    }
}