package com.example.cricbuzz.series.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class PointsTableData {

    @SerializedName("teamname")
    @Expose
     val teamname: String? = null

    @SerializedName("shortname")
    @Expose
     val shortname: String? = null

    @SerializedName("img")
    @Expose
     val img: String? = null

    @SerializedName("matches")
    @Expose
     val matches: Int? = null

    @SerializedName("wins")
    @Expose
     val wins: Int? = null

    @SerializedName("loss")
    @Expose
     val loss: Int? = null

    @SerializedName("ties")
    @Expose
     val ties: Int? = null

    @SerializedName("nr")
    @Expose
     val nr: Int? = null
}