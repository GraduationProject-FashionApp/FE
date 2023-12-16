package com.gradu.lookthat.views.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.gradu.lookthat.base.BaseActivity
import com.gradu.lookthat.databinding.ActivityLoadingBinding
import java.net.URI

class LoadingActivity: BaseActivity<ActivityLoadingBinding>(ActivityLoadingBinding::inflate) {
    lateinit var imageUri: String
    override fun initView() {
        imageUri = intent.getStringExtra("imageUri")!!
        Log.d("LoadingActivity: imageUri", imageUri)
    }

    override fun onStart() {
        super.onStart()
        setImage()
    }

    fun setImage() = with(binding){
        Log.d("LoadingActivity: setImage", "onStart")
        Glide.with(loadingImageIv).load(imageUri).into(loadingImageIv)
    }
}