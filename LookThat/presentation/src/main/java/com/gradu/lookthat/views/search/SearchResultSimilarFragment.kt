package com.gradu.lookthat.views.search

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.gradu.lookthat.R
import com.gradu.lookthat.adapter.SearchResultRVAdapter
import com.gradu.lookthat.base.BaseFragment
import com.gradu.lookthat.databinding.FragmentSearchResultSimilarBinding
import com.gradu.lookthat.di.MyApplication.Companion.sRetrofit
import com.gradu.lookthat.views.search.api.APIinterface
import com.gradu.lookthat.views.search.api.Item
import com.gradu.lookthat.views.search.api.SearchResponse
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
    override fun initView() {
        super.initView()
        arguments?.let {
            imageUri =
                if(Build.VERSION.SDK_INT >= 33) it.getParcelable("imageUri", Uri::class.java)!!
                else @Suppress("DEPRECATION") it.getParcelable("imageUri") as? Uri!!
        }
        Log.d("imageUri", "$imageUri")
        getSearchResult()
    }
    private fun initRecycler(response: SearchResponse) {
        binding.fragmentSearchResultSimilarRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            val dataList = response.topList.plus(response.bottomList)
            searchResultAdapter = SearchResultRVAdapter(dataList)
            searchResultAdapter.setMyItemClickListener(object : SearchResultRVAdapter.MyItemClickListener{
                override fun onItemClick(itemList: List<Item>, position: Int) {
                    val intent = Intent(context, SearchProductDetailActivity::class.java)
                        .putExtra("purchaseLink", itemList[position].purchaseLink)
                        .putExtra("image", itemList[position].image)
                        .putExtra("title", itemList[position].title)
                        .putExtra("price", itemList[position].price)
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
        val body = MultipartBody.Part.createFormData("file", file.name, requestBody)

        Log.d("image", "imgPath $imgPath")
        Log.d("image", "body $body")

        sRetrofit.create(APIinterface::class.java).getSearchResult(body)
            .enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    // 성공적으로 이미지 업로드 완료
                    response.body()?.let { initRecycler(it) }
                    Log.d("getSearchResult", "Image upload successful: ${response.body()}")
                } else {
                    // 이미지 업로드 실패
                    Log.e("getSearchResult", "Image upload failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                // 네트워크 오류 또는 예외 처리
                Log.e("getSearchResult", "Image upload failed: ${t.message}")
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
}