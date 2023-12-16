package com.gradu.lookthat.views.search

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.gradu.lookthat.base.BaseActivity
import com.gradu.lookthat.databinding.ActivityLoadingBinding
import java.net.URI

class LoadingActivity: BaseActivity<ActivityLoadingBinding>(ActivityLoadingBinding::inflate) {
    lateinit var imageUri: Uri
    override fun initView() {
        if(Build.VERSION.SDK_INT >= 33)
            imageUri = intent.getParcelableExtra("imageUri", Uri::class.java)!!
        else  imageUri = (@Suppress("DEPRECATION")intent.getParcelableExtra("imageUri") as? Uri)!!
        Log.d("LoadingActivity: imageUri", imageUri.toString())
        setImage()
    }

    fun setImage() = with(binding){
        Log.d("LoadingActivity: setImage", "onStart")
        Glide.with(loadingImageIv).load(imageUri).into(loadingImageIv)
    }

    fun activityStart(){
        startActivity(
            Intent(this, SearchResultActivity::class.java)
                .putExtra("imageUri", imageUri))
    }
}