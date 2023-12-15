package com.gradu.lookthat.views.search

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.webkit.WebView
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.gradu.lookthat.base.BaseActivity
import com.gradu.lookthat.databinding.ActivityProductDetailBinding

class SearchProductDetailActivity : BaseActivity<ActivityProductDetailBinding>(ActivityProductDetailBinding::inflate) {
    private lateinit var purchaseLink : String
    private lateinit var image : String
    private lateinit var title : String
    private lateinit var price : String
    override fun initView() {
        purchaseLink = intent.getStringExtra("purchaseLink")!!
        image = intent.getStringExtra("image")!!
        title = intent.getStringExtra("title")!!
        price = intent.getIntExtra("price", 0).toString()

        with(binding){
            activityProductDetailProductTitleTv.text = title
            Glide.with(this@SearchProductDetailActivity)
                .load(image).into(activityProductDetailProductImgIv)
            activityProductDetailPriceTv.text = price
            // 뒤로가기
            activityProductDetailBackBtnIv.setOnClickListener {
                finish()
            }

            // 별 select
            activityProductDetailLikeBtnIv.setOnClickListener {

            }

            activityProductDetailLinkTv.setOnClickListener {
                startActivity(Intent(this@SearchProductDetailActivity, WebViewActivity::class.java)
                    .putExtra("purchaseLink", purchaseLink))
            }
        }

    }

}