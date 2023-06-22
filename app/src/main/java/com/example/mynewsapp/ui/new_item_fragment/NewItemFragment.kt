package com.example.mynewsapp.ui.new_item_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mynewsapp.R
import com.example.mynewsapp.databinding.NewitemfragmentBinding

class NewItemFragment : Fragment() {
    private var _binding: NewitemfragmentBinding? = null
    private val binding get() = _binding!!
    private val navArgs: NewItemFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewitemfragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val myWebView: WebView = view.findViewById(R.id.webView)
        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        myWebView.loadUrl(navArgs.newUrl)
        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.allowContentAccess = true
        myWebView.settings.domStorageEnabled = true
        myWebView.settings.useWideViewPort = true
        myWebView.settings.blockNetworkLoads = false
        myWebView.settings.mediaPlaybackRequiresUserGesture = false

        initListeners()
    }

    private fun initListeners() {

    }
}