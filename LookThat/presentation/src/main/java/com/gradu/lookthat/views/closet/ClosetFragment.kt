package com.gradu.lookthat.views.closet

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.gradu.lookthat.R
import com.gradu.lookthat.adapter.ClosetVPAdapter
import com.gradu.lookthat.base.BaseFragment
import com.gradu.lookthat.databinding.FragmentClosetBinding

class ClosetFragment : BaseFragment<FragmentClosetBinding>(R.layout.fragment_closet) {
    override fun initView() {
        super.initView()
        initTab()
    }
    private fun initTab() {
        val fragments = listOf(
            ClosetWholeFragment(),
            ClosetTopFragment(),
            ClosetOutwearFragment(),
            ClosetBottomFragment(),
            ClosetShoesFragment(),
        )
        val titles = listOf(
            "전체",
            "상의",
            "아우터",
            "하의",
            "신발",
        )
        val icons = listOf(
            R.drawable.selector_fragment_closet_tab_whole,
            R.drawable.selector_fragment_closet_tab_top,
            R.drawable.selector_fragment_closet_tab_outwear,
            R.drawable.selector_fragment_closet_tab_bottom,
            R.drawable.selector_fragment_closet_tab_shoes,
        )

        with(binding) {
            val adapter = ClosetVPAdapter(requireActivity(), fragments)
            fragmentClosetViewPager.adapter = adapter
            TabLayoutMediator(
                fragmentClosetTab,
                fragmentClosetViewPager
            ) { tab, position ->
                val view = LayoutInflater.from(requireContext()).inflate(R.layout.item_custom_tab, null)
                val imageView = view.findViewById<ImageView>(R.id.tab_icon)
                val textView = view.findViewById<TextView>(R.id.tab_text)
                imageView.setImageResource(icons[position])
                textView.text = titles[position]
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.grayAE))
                tab.customView = view
            }.attach()
            fragmentClosetTab.getTabAt(0)?.customView?.findViewById<TextView>(R.id.tab_text)
                ?.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            fragmentClosetTab.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.customView?.findViewById<TextView>(R.id.tab_text)
                        ?.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.customView?.findViewById<TextView>(R.id.tab_text)
                        ?.setTextColor(ContextCompat.getColor(requireContext(), R.color.grayAE))
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }

    }
}

