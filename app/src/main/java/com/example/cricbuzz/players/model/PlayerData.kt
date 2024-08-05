package com.example.cricbuzz.players.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class PlayerData{
@SerializedName("teamName")
@Expose
 val teamName: String? = null

@SerializedName("shortname")
@Expose
 val shortname: String? = null

@SerializedName("img")
@Expose
 val img: String? = null

@SerializedName("players")
@Expose
 var players: MutableList<PlayerList>? = null
}