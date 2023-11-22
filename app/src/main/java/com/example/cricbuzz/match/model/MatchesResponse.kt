package com.example.cricbuzz.match.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MatchesResponse {

    @SerializedName("apikey")
    @Expose
     val apikey: String? = null

    @SerializedName("data")
    @Expose
     val data: List<MatchesData>? = null

    @SerializedName("status")
    @Expose
     val status: String? = null

    @SerializedName("info")
    @Expose
     val info: Info? = null
}