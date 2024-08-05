package com.example.cricbuzz.match.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class BallbyBallData {

    @SerializedName("id")
    @Expose
     val id: String? = null

    @SerializedName("name")
    @Expose
     val name: String? = null

    @SerializedName("matchType")
    @Expose
     val matchType: String? = null

    @SerializedName("status")
    @Expose
     val status: String? = null

    @SerializedName("venue")
    @Expose
     val venue: String? = null

    @SerializedName("date")
    @Expose
     val date: String? = null

    @SerializedName("dateTimeGMT")
    @Expose
     val dateTimeGMT: String? = null

    @SerializedName("teams")
    @Expose
     val teams: List<String>? = null

    @SerializedName("teamInfo")
    @Expose
     val teamInfo: List<TeamInfo>? = null

    @SerializedName("score")
    @Expose
     val score: List<Score>? = null

    @SerializedName("tossWinner")
    @Expose
     val tossWinner: String? = null

    @SerializedName("tossChoice")
    @Expose
     val tossChoice: String? = null

    @SerializedName("matchWinner")
    @Expose
     val matchWinner: String? = null

    @SerializedName("series_id")
    @Expose
     val seriesId: String? = null

    @SerializedName("bbb")
    @Expose
     val bbb: List<Bbb>? = null

    @SerializedName("matchStarted")
    @Expose
     val matchStarted: Boolean? = null

    @SerializedName("matchEnded")
    @Expose
     val matchEnded: Boolean? = null
}