package com.gradu.lookthat.views

import android.net.Uri
import com.gradu.lookthat.R
import com.gradu.lookthat.adapter.SearchResultRVAdapter
import com.gradu.lookthat.base.BaseFragment
import com.gradu.lookthat.databinding.FragmentSearchResultRecommendBinding


class SearchResultRecommendFragment:
    BaseFragment<FragmentSearchResultRecommendBinding>(R.layout.fragment_search_result_recommend) {
    private var imageUri: Uri? = null
    lateinit var searchResultAdapter : SearchResultRVAdapter
    lateinit var productUrl: String
    override fun initView() {
        super.initView()
    }
}