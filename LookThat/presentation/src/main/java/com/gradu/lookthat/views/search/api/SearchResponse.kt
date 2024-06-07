package com.gradu.lookthat.views.search.api

data class SearchResponse(
    val results: List<Item>
)

data class Item(
    val id: Long,
    val data: List<String>
)