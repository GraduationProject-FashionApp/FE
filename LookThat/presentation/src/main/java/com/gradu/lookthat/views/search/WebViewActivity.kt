package com.gradu.lookthat.views.search

import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.gradu.lookthat.base.BaseActivity
import com.gradu.lookthat.databinding.WebViewBinding

class WebViewActivity : BaseActivity<WebViewBinding>(WebViewBinding::inflate) {
    override fun initView() {
        Log.d("WebViewActivity", "WebView : Started")
        with(binding.webview){
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            loadUrl(intent.getStringExtra("purchaseLink")!!)
        }

    }

    override fun onBackPressed() {
        val webView = binding.webview

        if (webView.canGoBack()) webView.goBack()
        else finish()

        super.onBackPressed()
    }

}