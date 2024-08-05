package com.example.cricbuzz.players.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class PlayerStat {


    @SerializedName("fn")
    @Expose
     val fn: String? = null

    @SerializedName("matchtype")
    @Expose
     val matchtype: String? = null

    @SerializedName("stat")
    @Expose
     val stat: String? = null

    @SerializedName("value")
    @Expose
     val value: String? = null

}
