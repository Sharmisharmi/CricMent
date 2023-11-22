package com.example.cricbuzz.match.viewmodel

import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzz.match.model.MatchesResponse
import com.example.cricbuzz.match.repository.MatchesRepository

class MatchesViewModel {

    private var matchesRepository: MatchesRepository? = null

    private var getLiveData: MutableLiveData<MatchesResponse> = MutableLiveData()
    private var getUpcomingData: MutableLiveData<MatchesResponse> = MutableLiveData()
    private var getFinishedData: MutableLiveData<MatchesResponse> = MutableLiveData()

//    fun getLiveMatchesDataList(
//        apiKey: String,
//        offset: Int,
//        live_recyclerView: RecyclerView,
//        no_data_availableLL: LinearLayout
//    ): MutableLiveData<MatchesResponse> {
//        getLiveData = matchesRepository!!.requestLiveMatchesDataList(apiKey,offset,live_recyclerView,no_data_availableLL)
//        return getLiveData
//    }


//    fun getUpcomingMatchesDataList(
//        apiKey: String,
//        live_recyclerView: RecyclerView,
//        no_data_availableLL: LinearLayout
//    ): MutableLiveData<MatchesResponse> {
//        getUpcomingData = matchesRepository!!.requestUpcomingMatchesDataList(apiKey,live_recyclerView,no_data_availableLL)
//        return getUpcomingData
//    }


    fun getFinishedMatchesDataList(
        apiKey: String,offset: Int
    ): MutableLiveData<MatchesResponse> {
        getFinishedData = matchesRepository!!.requestFinishedMatchesDataList(apiKey,offset)
        return getFinishedData
    }


//    fun getFinishedMatchesData(
//        apiKey: String
//    ): MutableLiveData<MatchesResponse> {
//        getFinishedData = matchesRepository!!.requestFinishedMatchesData(apiKey)
//        return getFinishedData
//    }

    init {

        matchesRepository = MatchesRepository()
    }
}