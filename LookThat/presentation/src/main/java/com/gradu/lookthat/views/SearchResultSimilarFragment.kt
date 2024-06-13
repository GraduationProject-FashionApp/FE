package com.gradu.lookthat.views

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.gradu.lookthat.R
import com.gradu.lookthat.adapter.SearchResultRVAdapter
import com.gradu.lookthat.base.BaseFragment
import com.gradu.lookthat.databinding.FragmentSearchResultSimilarBinding
import com.gradu.lookthat.base.MyApplication.Companion.sRetrofit
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


class SearchResultSimilarFragment:
    BaseFragment<FragmentSearchResultSimilarBinding>(R.layout.fragment_search_result_similar) {
    private var imageUri: Uri? = null
    lateinit var searchResultAdapter : SearchResultRVAdapter
    lateinit var productUrl: String
    lateinit var loadingDialog : LoadingDialog
    override fun initView() {
        super.initView()
        arguments?.let {
            imageUri =
                if(Build.VERSION.SDK_INT >= 33) it.getParcelable("imageUri", Uri::class.java)!!
                else @Suppress("DEPRECATION") it.getParcelable("imageUri") as? Uri!!
        }
        Log.d("imageUri", "$imageUri")
        loadingDialog = LoadingDialog(imageUri)
        getSearchResult()
        isLoading()
    }
    private fun initRecycler(response: List<Item>) {
        binding.fragmentSearchResultSimilarRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            searchResultAdapter = SearchResultRVAdapter(response)
            searchResultAdapter.setMyItemClickListener(object : SearchResultRVAdapter.MyItemClickListener{
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



    }
    private fun getSearchResult() {
        val imgPath = getRealPathFromURI()
        val file = File(imgPath)
        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("multipart_file", file.name, requestBody)

        Log.d("image", "imgPath $imgPath")
        Log.d("image", "body $body")

        sRetrofit.create(APIinterface::class.java).getSearchResult(body)
            .enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    // 성공적으로 이미지 업로드 완료
                    response.body()?.let {
                        loadingDialog.dismiss()
                        initRecycler(it.results) }
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
        val inputStream = context?.contentResolver?.openInputStream(imageUri!!)
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

    fun isLoading(){
        loadingDialog.isCancelable = false
        loadingDialog.show(parentFragmentManager, "Loading...")
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