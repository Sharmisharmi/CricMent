package com.example.cricbuzz.home.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class UFFData {


    @SerializedName("id")
    @Expose
     val id: String? = null

    @SerializedName("dateTimeGMT")
    @Expose
     val dateTimeGMT: String? = null

    @SerializedName("matchType")
    @Expose
     val matchType: String? = null

    @SerializedName("status")
    @Expose
     val status: String? = null

    @SerializedName("ms")
    @Expose
     val ms: String? = null

    @SerializedName("t1")
    @Expose
     val t1: String? = null

    @SerializedName("t2")
    @Expose
     val t2: String? = null

    @SerializedName("t1s")
    @Expose
     val t1s: String? = null

    @SerializedName("t2s")
    @Expose
     val t2s: String? = null

    @SerializedName("t1img")
    @Expose
     val t1img: String? = null

    @SerializedName("t2img")
    @Expose
     val t2img: String? = null
}