package com.gradu.lookthat.views.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.gradu.lookthat.R
import com.gradu.lookthat.base.BaseActivity
import com.gradu.lookthat.databinding.ActivitySearchResultBinding

class SearchResultActivity : BaseActivity<ActivitySearchResultBinding>(ActivitySearchResultBinding::inflate){
    override fun initView() {
        initToolbar()
        initViewPager()
        val uri = intent.getStringExtra("uri")
    }
    private fun initToolbar() {
        with(binding.toolbar) {
            logo = null
            navigationIcon = context.getDrawable(R.drawable.ic_back)
            setNavigationOnClickListener { finish() }
        }
    }
    private fun initViewPager() {
        val adapter = ViewPagerAdapter(this)
        binding.fragmentSearchResultVp.adapter = adapter
        val tabTitle = listOf("비슷한 옷", "함께 입을 옷")

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