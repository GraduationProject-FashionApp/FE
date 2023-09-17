package com.gradu.lookthat.views.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.gradu.lookthat.R
import com.gradu.lookthat.base.BaseFragment
import com.gradu.lookthat.databinding.FragmentSearchResultBinding

class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result){
    override fun initView() {
        super.initView()

        // ViewPager2 Adapter 설정
        val adapter = ViewPagerAdapter(requireActivity())
        binding.fragmentSearchResultVp.adapter = adapter
        val tabTitle = listOf<String>("비슷한 옷 보기", "함께 입을 옷 보기")
        // TabLayout과 ViewPager2 연결
        TabLayoutMediator(binding.fragmentSearchResultTab, binding.fragmentSearchResultVp) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }

    inner class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> SearchResultSimilarFragment()
                1 -> SearchResultRecommendFragment()
                else -> throw IllegalStateException("Invalid position $position")
            }
        }
    }
}