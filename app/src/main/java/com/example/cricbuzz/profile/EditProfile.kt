package com.example.cricbuzz.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.example.cricbuzz.MainActivity
import com.example.cricbuzz.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class EditProfile : AppCompatActivity() {

    lateinit var backImage:ImageView
    lateinit var nameEt:EditText
    lateinit var emailEt:EditText
    lateinit var profile_image:CircleImageView

    var getIntent=""
    var getMail=""
    var getPic=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        initView()

        getIntent= intent.getStringExtra("displayName").toString()
        getMail= intent.getStringExtra("displayMail").toString()
        getPic= intent.getStringExtra("displayPic").toString()

        nameEt.setText(getIntent)
        emailEt.setText(getMail)

        Picasso.get().load(getPic).into(profile_image)

        backImage.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun initView() {
        backImage = findViewById(R.id.backImage)
        nameEt = findViewById(R.id.nameEt)
        emailEt = findViewById(R.id.emailEt)
        profile_image = findViewById(R.id.profile_image)
    }
}