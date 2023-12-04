package com.gradu.lookthat.views.search

import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.gradu.lookthat.R
import com.gradu.lookthat.base.BaseActivity
import com.gradu.lookthat.databinding.ActivitySearchResultBinding

class SearchResultActivity : BaseActivity<ActivitySearchResultBinding>(ActivitySearchResultBinding::inflate){
    lateinit var imageUri : Uri
    override fun initView() {
        imageUri =
            if(SDK_INT >= 33) intent.getParcelableExtra("imageUri", Uri::class.java)!!
            else @Suppress("DEPRECATION") intent.getParcelableExtra("imageUri") as? Uri!!
        Log.d("SearchResultActivity", "Image URI: $imageUri")
        initToolbar()
        initViewPager()
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
            val fragment: Fragment = when (position) {
                0 -> SearchResultSimilarFragment()
                1 -> SearchResultRecommendFragment()
                else -> throw IllegalStateException("Invalid position $position")
            }

            fragment.arguments = Bundle().apply {
                putParcelable("imageUri", imageUri)
            }

            return fragment
        }
    }
}