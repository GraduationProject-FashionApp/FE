package com.gradu.lookthat.views.search

import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.recyclerview.widget.GridLayoutManager
import com.gradu.lookthat.R
import com.gradu.lookthat.adapter.ClosetRVAdapter
import com.gradu.lookthat.adapter.SearchResultRVAdapter
import com.gradu.lookthat.base.BaseFragment
import com.gradu.lookthat.databinding.FragmentSearchResultSimilarBinding
import com.gradu.lookthat.views.closet.ClosetRVItemDecoration
import java.io.File

class SearchResultSimilarFragment:
    BaseFragment<FragmentSearchResultSimilarBinding>(R.layout.fragment_search_result_similar) {
    private var imageUri: Uri? = null
    override fun initView() {
        super.initView()
        arguments?.let {
            imageUri =
                if(Build.VERSION.SDK_INT >= 33) it.getParcelable("imageUri", Uri::class.java)!!
                else @Suppress("DEPRECATION") it.getParcelable("imageUri") as? Uri!!
        }
        initRecycler()
    }
    private fun initRecycler() {
        val items = ArrayList<String>().apply {
            for (i in 1..50) {
                add("Item $i")
            }
        }

        binding.fragmentSearchResultSimilarRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = SearchResultRVAdapter(items)
        }

    }
    private fun uploadImageToServer(imageUri: Uri) {
        val file = File(getRealPathFromURI(imageUri)) // URI를 실제 파일 경로로 변환
        //val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        //val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        // Retrofit을 사용하여 서버로 Multipart 요청 전송
        //val service = RetrofitClient.createService(ApiService::class.java)
        //val call = service.uploadImage(body)

        /*call.enqueue(object : Callback<UploadResponse> {
            override fun onResponse(call: Call<UploadResponse>, response: Response<UploadResponse>) {
                if (response.isSuccessful) {
                    // 성공적으로 이미지 업로드 완료
                    val uploadResponse = response.body()
                    Log.d("SearchResultSimilarFragment", "Image upload successful: ${uploadResponse?.message}")
                } else {
                    // 이미지 업로드 실패
                    Log.e("SearchResultSimilarFragment", "Image upload failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                // 네트워크 오류 또는 예외 처리
                Log.e("SearchResultSimilarFragment", "Image upload failed: ${t.message}")
            }
        })*/
    }

    private fun getRealPathFromURI(uri: Uri): String {
        val cursor = context?.contentResolver?.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        val filePath = cursor?.getString(columnIndex ?: 0)
        cursor?.close()
        return filePath ?: ""
    }
}