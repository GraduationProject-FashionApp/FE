package com.gradu.lookthat.views.search.api

data class Item(
    val id: Int,
    val category: String,
    val style: String,
    val title: String,
    val price: Int,
    val image: String,
    val purchaseLink: String
)

data class SearchResponse(
    val topList: List<Item>,
    val bottomList: List<Item>
)
