package com.example.cricbuzz.series.view_model

import androidx.lifecycle.MutableLiveData
import com.example.cricbuzz.match.model.MatchesResponse
import com.example.cricbuzz.players.model.PlayerResponse
import com.example.cricbuzz.series.model.SeriesInfoRespnse
import com.example.cricbuzz.series.model.SeriesResponse
import com.example.cricbuzz.series.repository.SeriesRepository

class SeriesViewModel {

    private val seriesRepository:SeriesRepository = SeriesRepository()


    private var getSeriesData: MutableLiveData<SeriesResponse> = MutableLiveData()
    private var getSeriesInfoData: MutableLiveData<SeriesInfoRespnse> = MutableLiveData()
    private var getPlayerData: MutableLiveData<PlayerResponse> = MutableLiveData()


    fun getSeriesDataList(
        apiKey: String,offset: Int
    ): MutableLiveData<SeriesResponse> {
        getSeriesData = seriesRepository!!.requestSeriesList(apiKey,offset)
        return getSeriesData
    }


    fun getSeriesInfoData(
        apiKey: String,seriesId: String
    ): MutableLiveData<SeriesInfoRespnse> {
        getSeriesInfoData = seriesRepository!!.requestSeriesInfoData(apiKey,seriesId)
        return getSeriesInfoData
    }


    fun getPlayersList(
        apiKey: String,seriesId: String
    ): MutableLiveData<PlayerResponse> {
        getPlayerData = seriesRepository!!.requestPlayerData(apiKey,seriesId)
        return getPlayerData
    }


}