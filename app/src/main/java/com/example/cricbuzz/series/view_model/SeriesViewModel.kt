package com.example.cricbuzz.series.view_model

import androidx.lifecycle.MutableLiveData
import com.example.cricbuzz.match.model.MatchesResponse
import com.example.cricbuzz.series.model.SeriesResponse
import com.example.cricbuzz.series.repository.SeriesRepository

class SeriesViewModel {

    private val seriesRepository:SeriesRepository = SeriesRepository()


    private var getSeriesData: MutableLiveData<SeriesResponse> = MutableLiveData()


    fun getSeriesDataList(
        apiKey: String,offset: Int
    ): MutableLiveData<SeriesResponse> {
        getSeriesData = seriesRepository!!.requestSeriesList(apiKey,offset)
        return getSeriesData
    }
}