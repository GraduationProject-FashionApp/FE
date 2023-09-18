package com.gradu.lookthat.views.closet

import androidx.recyclerview.widget.GridLayoutManager
import com.gradu.lookthat.R
import com.gradu.lookthat.adapter.ClosetRVAdapter
import com.gradu.lookthat.base.BaseFragment
import com.gradu.lookthat.databinding.FragmentClosetShoesBinding

class ClosetShoesFragment : BaseFragment<FragmentClosetShoesBinding>(R.layout.fragment_closet_shoes) {
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

        binding.fragmentClosetShoesRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = ClosetRVAdapter(items)
            addItemDecoration(
                ClosetRVItemDecoration(
                    3,
                    resources.getDimensionPixelSize(R.dimen.horizontal_spacing),
                    resources.getDimensionPixelSize(R.dimen.vertical_spacing),
                    resources.getDimensionPixelSize(R.dimen.edge_spacing)
                )
            )
        }

    }
}