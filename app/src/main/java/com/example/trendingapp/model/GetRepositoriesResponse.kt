package com.example.trendingapp.model

data class GetRepositoriesResponse(
    val items: List<Item>? = emptyList()
)

data class Item(
    val photo: String? = null,
    val name: String? = null,
    val title: String? = "Android Developer",
    val htmlUrl: String? = null,
    val description: String? = null,
    val language: String? = null,
    val watchersCount: String? = null,
    val forksCount: String? = null,
    var isExpanded: Boolean = false
)