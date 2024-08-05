package com.example.cricbuzz.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.cricbuzz.home.model.CFResponse
import com.example.cricbuzz.home.model.InternationalSportsNewsResponse
import com.example.cricbuzz.home.model.UFFResponse
import com.example.cricbuzz.home.repository.HomeRepository
import com.example.cricbuzz.players.model.PlayerResponse

class HomeViewModel {

    var homeRepository:HomeRepository = HomeRepository()

    private var requestMatchesList: MutableLiveData<CFResponse> = MutableLiveData()
    private var requestSportsNewsList: MutableLiveData<InternationalSportsNewsResponse> = MutableLiveData()

    fun getMatchesList(
        apiKey: String
    ): MutableLiveData<CFResponse> {
        requestMatchesList = homeRepository!!.requestMatchesData(apiKey)
        return requestMatchesList
    }

    fun getSportsNewsList(
        apiKey: String
    ): MutableLiveData<InternationalSportsNewsResponse> {
        requestSportsNewsList = homeRepository!!.requestSportsNewsData(apiKey)
        return requestSportsNewsList
    }
}