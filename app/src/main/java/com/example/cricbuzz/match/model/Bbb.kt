package com.example.cricbuzz.match.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Bbb {


    @SerializedName("n")
    @Expose
     val n: Int? = null

    @SerializedName("inning")
    @Expose
     val inning: Int? = null

    @SerializedName("over")
    @Expose
     val over: Int? = null

    @SerializedName("ball")
    @Expose
     val ball: Int? = null

    @SerializedName("batsman")
    @Expose
     val batsman: Batsman? = null

    @SerializedName("bowler")
    @Expose
     val bowler: Bowler? = null

    @SerializedName("runs")
    @Expose
     val runs: Int? = null

    @SerializedName("penalty")
    @Expose
     val penalty: String? = null

    @SerializedName("extras")
    @Expose
     val extras: Int? = null

}
