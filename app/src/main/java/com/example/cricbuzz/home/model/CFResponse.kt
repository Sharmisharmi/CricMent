package com.example.cricbuzz.home.model

import com.example.cricbuzz.match.model.Info
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class CFResponse {

    @SerializedName("apikey")
    @Expose
     val apikey: String? = null

    @SerializedName("data")
    @Expose
     val data: List<CFData>? = null

    @SerializedName("status")
    @Expose
     val status: String? = null

    @SerializedName("info")
    @Expose
     val info: Info? = null
}