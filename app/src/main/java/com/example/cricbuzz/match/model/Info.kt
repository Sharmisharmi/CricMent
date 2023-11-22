package com.example.cricbuzz.match.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Info {


    @SerializedName("hitsToday")
    @Expose
     val hitsToday: Int? = null

    @SerializedName("hitsUsed")
    @Expose
     val hitsUsed: Int? = null

    @SerializedName("hitsLimit")
    @Expose
     val hitsLimit: Int? = null

    @SerializedName("credits")
    @Expose
     val credits: Int? = null

    @SerializedName("server")
    @Expose
     val server: Int? = null

    @SerializedName("offsetRows")
    @Expose
     val offsetRows: Int? = null

    @SerializedName("totalRows")
    @Expose
     val totalRows: Int? = null

    @SerializedName("queryTime")
    @Expose
     val queryTime: Double? = null

    @SerializedName("s")
    @Expose
     val s: Int? = null

    @SerializedName("cache")
    @Expose
     val cache: Int? = null

}
