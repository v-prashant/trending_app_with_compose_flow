package com.example.trendingapp.Trending

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendingapp.model.GetRepositoriesResponse
import com.example.trendingapp.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrendingViewModel @Inject constructor(private val repository: TrendingRepository): ViewModel() {

    private val _repositories = MutableStateFlow<List<Item>>(emptyList())
    val repositories = _repositories.asStateFlow()

    fun getRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getRepositories()
                if(response.isSuccessful && response.body() != null){
                    _repositories.value = response.body()?.items ?: emptyList()
                }else{
                    _repositories.value = emptyList()
                    Log.d("NETWORK_EXCEPTION", "Response body or items is null")
                }
            } catch (e: Exception) {
                _repositories.value = emptyList()
                Log.d("NETWORK_EXCEPTION", e.stackTraceToString())
            }
        }
    }

}