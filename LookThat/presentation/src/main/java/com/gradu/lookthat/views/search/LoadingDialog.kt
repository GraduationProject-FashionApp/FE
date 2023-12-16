package com.gradu.lookthat.views.search

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.gradu.lookthat.R
import com.gradu.lookthat.databinding.ActivityLoadingBinding
import com.gradu.lookthat.databinding.DialogFragmentSearchCheckBinding

class LoadingDialog(var imageUri: Uri?): DialogFragment() {
    private lateinit var binding: ActivityLoadingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        Log.d("Loading..", "LoadingDialog.onCreateView")
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_white)
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        binding = ActivityLoadingBinding.inflate(inflater, container, false)

        Log.d("imageUri in LoadingDialog", imageUri.toString())
        Glide.with(binding.loadingImageIv).load(imageUri).into(binding.loadingImageIv)

        return binding.root
    }

//    fun close(){
//        Log.d("dismiss called", "LoadingDialog.close")
//        dismiss()
//    }
}