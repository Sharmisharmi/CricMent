package com.example.cricbuzz.series.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class MatchInfo {


    @SerializedName("id")
    @Expose
     val id: String? = null

    @SerializedName("name")
    @Expose
     val name: String? = null

    @SerializedName("startdate")
    @Expose
     val startdate: String? = null

    @SerializedName("enddate")
    @Expose
     val enddate: String? = null

    @SerializedName("odi")
    @Expose
     val odi: Int? = null

    @SerializedName("t20")
    @Expose
     val t20: Int? = null

    @SerializedName("test")
    @Expose
     val test: Int? = null

    @SerializedName("squads")
    @Expose
     val squads: Int? = null

    @SerializedName("matches")
    @Expose
     val matches: Int? = null
}