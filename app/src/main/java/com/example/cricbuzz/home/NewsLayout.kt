package com.example.cricbuzz.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cricbuzz.MainActivity
import com.example.cricbuzz.R
import com.example.cricbuzz.databinding.ActivityNewsLayoutBinding

class NewsLayout : AppCompatActivity() {

    private lateinit var binding: ActivityNewsLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backImage.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}