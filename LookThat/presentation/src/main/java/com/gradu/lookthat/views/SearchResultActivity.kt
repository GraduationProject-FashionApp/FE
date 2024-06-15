package com.gradu.lookthat.views

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.gradu.lookthat.R
import com.gradu.lookthat.adapter.SearchResultRVAdapter
import com.gradu.lookthat.base.BaseActivity
import com.gradu.lookthat.base.MyApplication
import com.gradu.lookthat.databinding.ActivitySearchResultBinding
import com.gradu.lookthat.views.api.APIinterface
import com.gradu.lookthat.views.api.Item
import com.gradu.lookthat.views.api.SearchResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

class SearchResultActivity :
    BaseActivity<ActivitySearchResultBinding>(ActivitySearchResultBinding::inflate) {
    private var imageUri: Uri? = null
    lateinit var searchResultAdapter: SearchResultRVAdapter
    lateinit var productUrl: String
    lateinit var loadingDialog: LoadingDialog
    override fun initView() {
        imageUri =
            if (SDK_INT >= 33) intent.getParcelableExtra("imageUri", Uri::class.java)!!
            else @Suppress("DEPRECATION") intent.getParcelableExtra("imageUri") as? Uri!!
        Log.d("SearchResultActivity", "Image URI: $imageUri")
        loadingDialog = LoadingDialog(imageUri)
        getSearchResult()
        isLoading()
        binding.resultBgImgIv.setColorFilter(Color.parseColor("#96000000"))
        binding.resultBgImgIv.setImageURI(imageUri)
        binding.resultPrevIv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
//        initToolbar()
//        initViewPager()
    }

    //    private fun initToolbar() {
//        with(binding.toolbar) {
//            logo = null
//            navigationIcon = context.getDrawable(R.drawable.ic_back)
//            setNavigationOnClickListener { finish() }
//        }
//    }
//    private fun initViewPager() {
//        val adapter = ViewPagerAdapter(this)
//        binding.fragmentSearchResultVp.adapter = adapter
//        val tabTitle = listOf("비슷한 옷", "함께 입을 옷")
//
//        TabLayoutMediator(binding.fragmentSearchResultTab, binding.fragmentSearchResultVp) { tab, position ->
//            tab.text = tabTitle[position]
//        }.attach()
//    }
//    inner class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
//        override fun getItemCount(): Int = 2
//
//        override fun createFragment(position: Int): Fragment {
//            val fragment: Fragment = when (position) {
//                0 -> SearchResultSimilarFragment()
//                1 -> SearchResultRecommendFragment()
//                else -> throw IllegalStateException("Invalid position $position")
//            }
//
//            fragment.arguments = Bundle().apply {
//                putParcelable("imageUri", imageUri)
//            }
//
//            return fragment
//        }
//    }
    private fun initRecycler(response: List<Item>) {
        binding.resultSimilarRv.apply {
            layoutManager = GridLayoutManager(this@SearchResultActivity, 2)
            searchResultAdapter = SearchResultRVAdapter(response)
            searchResultAdapter.setMyItemClickListener(object :
                SearchResultRVAdapter.MyItemClickListener {
                override fun onItemClick(itemList: List<Item>, position: Int) {
                    val intent = Intent(context, SearchProductDetailActivity::class.java)
                        .putExtra("purchaseLink", itemList[position].data[LINK])
                        .putExtra("image", itemList[position].data[URL])
                        .putExtra("title", itemList[position].data[TITLE])
                        .putExtra("price", itemList[position].data[DISCOUNT_PRICE])
                    startActivity(intent)
                }

            })

            adapter = searchResultAdapter
        }

        binding.resultCountTextTv.text = "${response.size}"

    }

    private fun getSearchResult() {
        val imgPath = getRealPathFromURI()
        val file = File(imgPath)
        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("multipart_file", file.name, requestBody)

        Log.d("image", "imgPath $imgPath")
        Log.d("image", "body $body")

        MyApplication.sRetrofit.create(APIinterface::class.java).getSearchResult(body)
            .enqueue(object : Callback<SearchResponse> {
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if (response.isSuccessful) {
                        // 성공적으로 이미지 업로드 완료
                        response.body()?.let {
                            loadingDialog.dismiss()
                            initRecycler(it.results)
                        }
                        Log.d("getSearchResult", "Image upload successful: ${response.body()}")
                    } else {
                        // 이미지 업로드 실패
                        Log.e("getSearchResult", "Image upload failed: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    // 네트워크 오류 또는 예외 처리
                    Log.e("getSearchResult", "onFailure: ${t.message}")
                }
            })
    }

    private fun getRealPathFromURI(): String {
        val inputStream = this?.contentResolver?.openInputStream(imageUri!!)
        val tempFile = createTempFileFromInputStream(inputStream)
        return tempFile?.absolutePath ?: ""
    }

    private fun createTempFileFromInputStream(inputStream: InputStream?): File? {
        if (inputStream == null) return null

        try {
            val tempFile = File.createTempFile("temp_image", null)
            tempFile.deleteOnExit()
            FileOutputStream(tempFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
            return tempFile
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun isLoading() {
        loadingDialog.isCancelable = false
        loadingDialog.show(supportFragmentManager, "Loading...")
    }

    companion object {
        //제품명,가격,할인가격,할인율,사진위치,키워드,구매링크
        const val TITLE = 0
        const val PRICE = 1
        const val DISCOUNT_PRICE = 2
        const val DISCOUNT_RATE = 3
        const val URL = 4
        const val KEYWORD = 5
        const val LINK = 6
    }
}