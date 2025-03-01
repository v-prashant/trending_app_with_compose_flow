package com.example.trendingapp.Trending

import com.example.trendingapp.api.APIServices
import javax.inject.Inject

class TrendingRepository @Inject constructor(private val apiService: APIServices)  {
    suspend fun getRepositories() = apiService.getRepositories("stars", "100")
}