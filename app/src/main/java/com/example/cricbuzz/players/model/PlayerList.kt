package com.example.cricbuzz.players.model

import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class PlayerList: Serializable {


    @SerializedName("id")
    @Expose
     val id: String? = null

    @SerializedName("name")
    @Expose
     val name: String? = null

    @SerializedName("role")
    @Expose
     val role: String? = null

    @SerializedName("battingStyle")
    @Expose
     val battingStyle: String? = null

    @SerializedName("bowlingStyle")
    @Expose
     val bowlingStyle: String? = null

    @SerializedName("country")
    @Expose
     val country: String? = null

    @SerializedName("playerImg")
    @Expose
     val playerImg: String? = null
}
