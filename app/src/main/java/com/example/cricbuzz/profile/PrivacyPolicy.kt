package com.example.cricbuzz.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.cricbuzz.R

class PrivacyPolicy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        var web =findViewById(R.id.webView) as WebView
        web.loadUrl("file:///android_asset/e82ec849-8e0b-44a9-895f-e432d2247d46_en.html");
    }
}