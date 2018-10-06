package com.matsunaopen.repositoryviewer.view

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import com.matsunaopen.repositoryviewer.R


/**
 * Created by DevUser on 2018/10/07.
 */
class RepositoryDialog : DialogFragment() {
    val URL_KEY: String = "URL_KEY"

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = View.inflate(activity, R.layout.layout_repository_web_view, null)
        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
        val url = arguments.getString(URL_KEY)
        view.apply {
            val webView = findViewById<WebView>(R.id.readme_shown_area)
            webView.webViewClient = getWebViewClient()
            webView.loadUrl(url)

            findViewById<Button>(R.id.readme_close_button).setOnClickListener {
                dismiss()
            }
        }
        return builder.create()
    }

    private fun getWebViewClient(): WebViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {

        }
    }

}
