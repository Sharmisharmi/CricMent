package com.example.cricbuzz.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.cricbuzz.R

class AboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        var web =findViewById(R.id.webView) as WebView
        web.loadUrl("file:///android_asset/about_us.html");
    }
}