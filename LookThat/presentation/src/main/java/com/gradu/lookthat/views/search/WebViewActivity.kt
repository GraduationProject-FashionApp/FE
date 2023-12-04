package com.gradu.lookthat.views.search

import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.gradu.lookthat.base.BaseActivity
import com.gradu.lookthat.databinding.WebViewBinding

class WebViewActivity : BaseActivity<WebViewBinding>(WebViewBinding::inflate) {
    override fun initView() {
        with(binding.webview){
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            loadUrl(intent.getStringExtra("productUrl")!!)
        }

    }

    override fun onBackPressed() {
        val webView = binding.webview

        if (webView.canGoBack()) webView.goBack()
        else finish()

        super.onBackPressed()
    }

}