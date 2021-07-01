package com.bci.jawwalpaysdk

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("SetJavaScriptEnabled,ObsoleteSdkInt")
class MainActivity : AppCompatActivity() {
    var PaymentKey: String? = null
    var IframeID = 0

    fun StartPayment(paymentKey: String?, iframeID: Int) {
        val mywebview = findViewById<View>(R.id.webview) as WebView
        mywebview.settings.javaScriptEnabled = true
        mywebview.webViewClient = WebViewClient()
        mywebview.settings.loadWithOverviewMode = true
        mywebview.settings.useWideViewPort = true
        mywebview.settings.setSupportZoom(true)
        mywebview.settings.builtInZoomControls = false
        mywebview.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        mywebview.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        mywebview.settings.domStorageEnabled = true
        mywebview.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        mywebview.isScrollbarFadingEnabled = true
        if (Build.VERSION.SDK_INT < 18) {
            mywebview.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        }
        mywebview.loadUrl("https://accept.paymobsolutions.com/api/acceptance/iframes/$IframeID?payment_token=$PaymentKey")
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = intent
        PaymentKey = intent.getStringExtra(IntentKeys.PAYMENT_KEY)
        IframeID = intent.getIntExtra(java.lang.String.valueOf(IntentKeys.IFRAMEID), 1)
        StartPayment(PaymentKey, IframeID)
    }
}