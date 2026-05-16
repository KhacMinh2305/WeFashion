package com.minhdk.wefashion.presentation.ui.screen.cart.process_payment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri

@Composable
fun ProcessPaymentScreen(
    contentPadding: PaddingValues,
    paymentLink: String
) {
    WebViewBox(
        url = paymentLink,
        returnHost = "wefashionserver.onrender.com",
        modifier = Modifier.fillMaxSize().padding(contentPadding)
    )
}

@Composable
fun WebViewBox(
    url: String,
    returnHost: String,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        val uri = request?.url ?: return false
                        if (uri.host == returnHost) {
                            return try {
                                val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                                    addCategory(Intent.CATEGORY_BROWSABLE)
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                }
                                context.startActivity(intent)
                                true
                            } catch (e: ActivityNotFoundException) {
                                false
                            }
                        }
                        return false
                    }
                }
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        },
        update = { _ -> },
        onRelease = { webView ->
            webView.stopLoading()
            webView.destroy()
        }
    )
}