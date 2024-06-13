package com.gradu.lookthat.views

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.gradu.lookthat.base.BaseActivity
import com.gradu.lookthat.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) { openCamera() }
        else {
            Toast.makeText(this, "카메라 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
        }
    }
    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
        if (bitmap != null) {
            val imageUri = bitmapToFile(bitmap, this)
            Log.d("SearchFragment", "takePictureLauncher: $imageUri")
            checkImageDialog(imageUri)
        } else {
            Log.d("SearchFragment", "takePictureLauncher: Bitmap is null")
        }
    }

    private val selectImageFromGalleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            Log.d("SearchFragment", "selectImageFromGalleryLauncher: $uri")
            checkImageDialog(uri)
        }
    }

    override fun initView() {
        initListener()
    }
    private fun initListener() {
        with(binding) {
            fragmentSearchGalleryBtnCl.setOnClickListener {
                openGallery()
            }
            fragmentSearchCameraBtnCl.setOnClickListener {
                requestCameraPermission()
            }
        }

    }
    private fun openCamera() {
        takePictureLauncher.launch(null)
    }
    private fun openGallery() {
        selectImageFromGalleryLauncher.launch("image/*")
    }
    // 권한 요청
    private fun requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        } else {
            openCamera()
        }
    }

    private fun bitmapToFile(bitmap: Bitmap, context: Context): Uri {
        // 일시적인 파일 이름
        val fileName = "tempImage-${System.currentTimeMillis()}.jpg"

        // 외부 파일 디렉토리에 앱 전용 하위 디렉토리를 만듭니다.
        val directory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        // 파일 생성
        val imageFile = File(directory, fileName)

        // 파일에 Bitmap 내용을 저장
        FileOutputStream(imageFile).use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        }

        return Uri.fromFile(imageFile)
    }

    private fun checkImageDialog(imageUri: Uri) {
        val dialog = DialogSearchCheckFragment(imageUri)
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, "DialogSearchCheckFragment")
    }
}