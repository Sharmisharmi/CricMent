package com.example.cricbuzz.login

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class LoginData {


    @SerializedName("image")
    @Expose
     val image: String? = null

    @SerializedName("name")
    @Expose
     val name: String? = null

    @SerializedName("email")
    @Expose
     val email: String? = null

    @SerializedName("_id")
    @Expose
     val id: String? = null

    @SerializedName("__v")
    @Expose
     val v: Int? = null

}
