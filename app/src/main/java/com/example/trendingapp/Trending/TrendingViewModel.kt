package com.example.trendingapp.Trending

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendingapp.api.UiState
import com.example.trendingapp.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(private val repository: TrendingRepository): ViewModel() {

    private val _repositories = MutableStateFlow<UiState<List<Item>>>(UiState.Loading)
    val repositories = _repositories.asStateFlow()

    fun getRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getRepositories()
                if(response.isSuccessful && response.body() != null){
                    _repositories.value = UiState.Success(response.body()!!.items!!)
                }else{
                    _repositories.value = UiState.Error("Response body or items is null")
                    Log.d("NETWORK_EXCEPTION", "Response body or items is null")
                }
            } catch (e: Exception) {
                _repositories.value = UiState.Error(e.stackTraceToString())
                Log.d("NETWORK_EXCEPTION", e.stackTraceToString())
            }
        }
    }

}