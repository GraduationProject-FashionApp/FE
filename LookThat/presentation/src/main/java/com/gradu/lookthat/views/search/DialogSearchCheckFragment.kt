package com.gradu.lookthat.views.search

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.gradu.lookthat.databinding.DialogFragmentSearchCheckBinding

class DialogSearchCheckFragment(val imageUri: Uri) : DialogFragment() {
    private lateinit var binding: DialogFragmentSearchCheckBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = DialogFragmentSearchCheckBinding.inflate(inflater, container, false)
        setDialog()
        return binding.root
    }

    private fun setDialog() = with(binding) {
        // Transparent background for visible corner radius
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        Glide.with(fragmentSearchCheckIv).load(imageUri).into(fragmentSearchCheckIv)

        fragmentSearchCheckYesBtn.setOnClickListener {
            // 이미지를 LoadingActivity로 전달
            startActivity(
                Intent(requireContext(), LoadingActivity::class.java)
                .putExtra("imageUri", imageUri.toString()))
            dismiss()
        }
        // Set click event of cancel button
        fragmentSearchCheckNoBtn.setOnClickListener {
            dismiss()
        }
    }
}