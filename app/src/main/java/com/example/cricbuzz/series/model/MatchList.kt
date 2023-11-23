package com.example.cricbuzz.series.model

import com.example.cricbuzz.match.model.TeamInfo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class MatchList {

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

    @SerializedName("fantasyEnabled")
    @Expose
     val fantasyEnabled: Boolean? = null

    @SerializedName("bbbEnabled")
    @Expose
     val bbbEnabled: Boolean? = null

    @SerializedName("hasSquad")
    @Expose
     val hasSquad: Boolean? = null

    @SerializedName("matchStarted")
    @Expose
     val matchStarted: Boolean? = null

    @SerializedName("matchEnded")
    @Expose
     val matchEnded: Boolean? = null

    @SerializedName("teamInfo")
    @Expose
     val teamInfo: List<TeamInfo>? = null
}