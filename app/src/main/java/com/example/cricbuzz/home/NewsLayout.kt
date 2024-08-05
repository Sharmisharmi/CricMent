package com.example.cricbuzz.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cricbuzz.MainActivity
import com.example.cricbuzz.R
import com.example.cricbuzz.databinding.ActivityNewsLayoutBinding
import com.example.cricbuzz.home.model.ArticleData

class NewsLayout : AppCompatActivity() {

    private lateinit var binding: ActivityNewsLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var getIntent = intent.getSerializableExtra("NewsData") as ArticleData

        binding.newsName.text = getIntent.source!!.name.toString()
        binding.newsTitle.text = getIntent.title.toString()
        binding.newsDesc.text = getIntent.description.toString()
        binding.newsContent.text = getIntent.content.toString()
        binding.newsDate.text = getIntent.publishedAt.toString()

        binding.readMorecard.setOnClickListener {
            val uri = Uri.parse(getIntent.url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        binding.backImage.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}