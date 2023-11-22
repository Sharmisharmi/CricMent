package com.example.cricbuzz.profile

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.cricbuzz.MainActivity
import com.example.cricbuzz.R


class ConnectWithUs : AppCompatActivity() {

    lateinit var backImage:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect_with_us)

        backImage = findViewById(R.id.backImage)

        backImage.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    fun instaOnclick(view:View){
        try {
            // mediaLink is something like "https://instagram.com/p/6GgFE9JKzm/" or
            // "https://instagram.com/_u/sembozdemir"
            val uri = Uri.parse("https://instagram.com/crex.live?igshid=MzRlODBiNWFlZA==")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.instagram.android")
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.e("TAG", e.message!!)
        }
    }

}