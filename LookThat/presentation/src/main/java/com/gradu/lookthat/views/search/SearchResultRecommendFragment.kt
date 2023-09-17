package com.gradu.lookthat.views.search

import androidx.recyclerview.widget.GridLayoutManager
import com.gradu.lookthat.R
import com.gradu.lookthat.adapter.ClosetRVAdapter
import com.gradu.lookthat.base.BaseFragment
import com.gradu.lookthat.databinding.FragmentSearchResultRecommendBinding
import com.gradu.lookthat.databinding.FragmentSearchResultSimilarBinding
import com.gradu.lookthat.views.closet.ClosetRVItemDecoration

class SearchResultRecommendFragment:
    BaseFragment<FragmentSearchResultRecommendBinding>(R.layout.fragment_search_result_recommend) {
    override fun initView() {
        super.initView()
        initRecycler()
    }
    private fun initRecycler() {
        val items = ArrayList<String>().apply {
            for (i in 1..50) {
                add("Item $i")
            }
        }

        binding.fragmentSearchResultRecommendRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = ClosetRVAdapter(items)
            addItemDecoration(
                ClosetRVItemDecoration(
                    3,
                    resources.getDimensionPixelSize(R.dimen.fragment_closet_item_horizontal),
                    resources.getDimensionPixelSize(R.dimen.fragment_closet_item_vertical)
                )
            )
        }

    }
}