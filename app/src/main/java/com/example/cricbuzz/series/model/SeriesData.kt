package com.example.cricbuzz.series.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class SeriesData {


    @SerializedName("id")
    @Expose
     val id: String? = null

    @SerializedName("name")
    @Expose
     val name: String? = null

    @SerializedName("startDate")
    @Expose
     val startDate: String? = null

    @SerializedName("endDate")
    @Expose
     val endDate: String? = null

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