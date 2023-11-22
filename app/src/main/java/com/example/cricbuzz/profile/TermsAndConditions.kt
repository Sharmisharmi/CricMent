package com.example.cricbuzz.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.cricbuzz.R

class TermsAndConditions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_conditions)

        var web =findViewById(R.id.webView) as WebView
        web.loadUrl("file:///android_asset/terms_and_conditions.html");
    }
}