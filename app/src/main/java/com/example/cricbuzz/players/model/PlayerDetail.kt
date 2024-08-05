package com.example.cricbuzz.players.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class PlayerDetail {

    @SerializedName("id")
    @Expose
     val id: String? = null

    @SerializedName("name")
    @Expose
     val name: String? = null

    @SerializedName("dateOfBirth")
    @Expose
     val dateOfBirth: String? = null

    @SerializedName("role")
    @Expose
     val role: String? = null

    @SerializedName("battingStyle")
    @Expose
     val battingStyle: String? = null

    @SerializedName("placeOfBirth")
    @Expose
     val placeOfBirth: String? = null

    @SerializedName("country")
    @Expose
     val country: String? = null

    @SerializedName("playerImg")
    @Expose
     val playerImg: String? = null

    @SerializedName("stats")
    @Expose
     val stats: List<PlayerStat>? = null

}
