package com.example.cricbuzz.home.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cricbuzz.api.APInterface
import com.example.cricbuzz.api.ApiClient
import com.example.cricbuzz.home.model.CFResponse
import com.example.cricbuzz.home.model.InternationalSportsNewsResponse
import com.example.cricbuzz.home.model.UFFResponse
import com.example.cricbuzz.players.model.PlayerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {

    fun requestMatchesData(
        apiKey: String
    ): MutableLiveData<CFResponse> {
        val mutableLiveData: MutableLiveData<CFResponse> = MutableLiveData()


        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.getCFMatchInfo(apiKey,0)?.enqueue(object :
            Callback<CFResponse?> {
            override fun onResponse(call: Call<CFResponse?>?, response: Response<CFResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )

                    Log.d("response_raw", "onResponse: "+response.raw())



                }else{

                    Log.d("response_raw", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<CFResponse?>, t: Throwable?) {

                Log.d("response_raw", "onFailure: "+t.toString())



            }
        })

        return mutableLiveData
    }


    fun requestSportsNewsData(
        apiKey: String
    ): MutableLiveData<InternationalSportsNewsResponse> {
        val mutableLiveData: MutableLiveData<InternationalSportsNewsResponse> = MutableLiveData()


        val apiService: APInterface? = ApiClient.getNewsRetrofitClient()?.create(APInterface::class.java)
        apiService?.getSportsNewsList(apiKey,"in","sports")?.enqueue(object :
            Callback<InternationalSportsNewsResponse?> {
            override fun onResponse(call: Call<InternationalSportsNewsResponse?>?, response: Response<InternationalSportsNewsResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )

                    Log.d("response_raw", "onResponse: "+response.raw())



                }else{

                    Log.d("response_raw", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<InternationalSportsNewsResponse?>, t: Throwable?) {

                Log.d("response_raw", "onFailure: "+t.toString())



            }
        })

        return mutableLiveData
    }

}