package com.example.cricbuzz.match.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TeamInfo {


    @SerializedName("name")
    @Expose
     val name: String? = null

    @SerializedName("shortname")
    @Expose
     val shortname: String? = null

    @SerializedName("img")
    @Expose
     val img: String? = null

}
