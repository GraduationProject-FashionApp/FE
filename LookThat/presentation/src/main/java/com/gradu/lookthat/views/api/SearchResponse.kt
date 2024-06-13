package com.gradu.lookthat.views.api

data class SearchResponse(
    val results: List<Item>
)

data class Item(
    val id: Long,
    val data: List<String>
)