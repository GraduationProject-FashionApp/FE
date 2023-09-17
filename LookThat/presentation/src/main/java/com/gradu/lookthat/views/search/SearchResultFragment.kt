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
        initViewPager()

        // Navigation Args로부터 URI String을 받아옵니다.
        val args = SearchResultFragmentArgs.fromBundle(requireArguments())
        val imageUriString = args.imageUri // 가정: Args 이름이 imageUri 입니다.
        val uri = Uri.parse(imageUriString)

        // Glide를 사용하여 이미지 뷰에 이미지를 로드합니다.
        Glide.with(this)
            .load(uri)
            .into(binding.imageView) // 가정: FragmentSearchResultBinding에 imageView라는 이름의 ImageView가 있습니다.
    }

    private fun initViewPager() {
        val adapter = ViewPagerAdapter(requireActivity())
        binding.fragmentSearchResultVp.adapter = adapter
        val tabTitle = listOf("비슷한 옷 보기", "함께 입을 옷 보기")

        TabLayoutMediator(binding.fragmentSearchResultTab, binding.fragmentSearchResultVp) { tab, position ->
            tab.text = tabTitle[position]
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