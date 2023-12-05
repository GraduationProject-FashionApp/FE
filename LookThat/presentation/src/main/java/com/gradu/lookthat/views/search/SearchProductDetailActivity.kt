package com.gradu.lookthat.views.search

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.webkit.WebView
import androidx.core.os.bundleOf
import com.gradu.lookthat.base.BaseActivity
import com.gradu.lookthat.databinding.ActivityProductDetailBinding

class SearchProductDetailActivity : BaseActivity<ActivityProductDetailBinding>(ActivityProductDetailBinding::inflate) {
    lateinit var productUrl : String
    override fun initView() {

        productUrl = intent.getStringExtra("productUrl")!!

        with(binding){
            // 뒤로가기
            activityProductDetailBackBtnIv.setOnClickListener {
                finish()
            }

            // 별 select
            activityProductDetailLikeBtnIv.setOnClickListener {

            }

            activityProductDetailLinkTv.setOnClickListener {
                startActivity(Intent(this@SearchProductDetailActivity, WebViewActivity::class.java)
                    .putExtra("productUrl", productUrl))
            }
        }

    }

}