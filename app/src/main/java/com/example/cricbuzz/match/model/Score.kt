package com.example.cricbuzz.match.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Score {
    @SerializedName("r")
    @Expose
     val r: Int? = null

    @SerializedName("w")
    @Expose
     val w: Int? = null

    @SerializedName("o")
    @Expose
     val o: Double? = null

    @SerializedName("inning")
    @Expose
     val inning: String? = null

}
