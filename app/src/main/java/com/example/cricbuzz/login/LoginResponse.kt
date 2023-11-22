package com.example.cricbuzz.login

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class LoginResponse {

    @SerializedName("message")
    @Expose
     val message: String? = null

    @SerializedName("Data")
    @Expose
     val data: LoginData? = null

}
