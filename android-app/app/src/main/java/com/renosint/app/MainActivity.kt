package com.renosint.app

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import android.app.Activity

class MainActivity : Activity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = android.graphics.Color.rgb(7, 8, 11)
        window.navigationBarColor = android.graphics.Color.rgb(7, 8, 11)

        webView = WebView(this)
        webView.setBackgroundColor(android.graphics.Color.rgb(7, 8, 11))
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.databaseEnabled = true
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.setSupportMultipleWindows(false)
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return handleUrl(request.url.toString())
            }
        }

        setContentView(webView)
        if (savedInstanceState == null) {
            webView.loadUrl("https://raw.githack.com/Ren-10-06/osnitren/ui-refresh/index.html?v=7cbea8e")
        } else {
            webView.restoreState(savedInstanceState)
        }
    }

    private fun handleUrl(url: String): Boolean {
        val uri = Uri.parse(url)
        val scheme = uri.scheme ?: return false
        if (scheme == "http" || scheme == "https") {
            val host = uri.host?.lowercase() ?: return false
            if (host == "raw.githack.com" || host == "githack.com") return false
        }

        return try {
            startActivity(Intent(Intent.ACTION_VIEW, uri))
            true
        } catch (_: Exception) {
            Toast.makeText(this, "No app available to open this link", Toast.LENGTH_SHORT).show()
            true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        webView.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        if (::webView.isInitialized) webView.destroy()
        super.onDestroy()
    }
}
