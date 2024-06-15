package com.gradu.lookthat.views

import android.content.Intent
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
        price = intent.getStringExtra("price")!!

        /*purchaseLink = "https://www.musinsa.com/app/goods/3222963"
        image = "http://image.msscdn.net/images/goods_img/20230411/3222963/3222963_16817991152687_500.jpg"
        title = "라이크린넨 바이오 셔츠 ( 6Color )"
        price = "37800"*/

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