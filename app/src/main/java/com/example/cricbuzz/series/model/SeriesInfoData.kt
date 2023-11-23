package com.example.cricbuzz.series.model

import com.example.cricbuzz.match.model.Info
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SeriesInfoData {
    @SerializedName("info")
    @Expose
     val info: MatchInfo? = null

    @SerializedName("matchList")
    @Expose
     val matchList: List<MatchList>? = null
}