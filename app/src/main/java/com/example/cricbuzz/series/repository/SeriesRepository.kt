package com.example.cricbuzz.series.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cricbuzz.api.APInterface
import com.example.cricbuzz.api.ApiClient
import com.example.cricbuzz.players.model.PlayerInfoResponse
import com.example.cricbuzz.players.model.PlayerResponse
import com.example.cricbuzz.series.model.PointsTableData
import com.example.cricbuzz.series.model.PointsTableResponse
import com.example.cricbuzz.series.model.SeriesInfoRespnse
import com.example.cricbuzz.series.model.SeriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeriesRepository {



    fun requestSeriesList(
        apiKey: String,offset: Int
    ): MutableLiveData<SeriesResponse> {
        val mutableLiveData: MutableLiveData<SeriesResponse> = MutableLiveData()


        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.getSeriesList(apiKey,offset)?.enqueue(object :
            Callback<SeriesResponse?> {
            override fun onResponse(call: Call<SeriesResponse?>?, response: Response<SeriesResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )

                    Log.d("response_raw", "onResponse: "+response.raw())



                }else{

                    Log.d("response_raw", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<SeriesResponse?>, t: Throwable?) {

                Log.d("response_raw", "onFailure: "+t.toString())



            }
        })

        return mutableLiveData
    }

    fun requestSeriesInfoData(
        apiKey: String,seriesId: String
    ): MutableLiveData<SeriesInfoRespnse> {
        val mutableLiveData: MutableLiveData<SeriesInfoRespnse> = MutableLiveData()


        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.getSeriesInfo(apiKey,seriesId)?.enqueue(object :
            Callback<SeriesInfoRespnse?> {
            override fun onResponse(call: Call<SeriesInfoRespnse?>?, response: Response<SeriesInfoRespnse?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )

                    Log.d("response_raw", "onResponse: "+response.raw())


                }else{

                    Log.d("response_raw", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<SeriesInfoRespnse?>, t: Throwable?) {

                Log.d("response_raw", "onFailure: "+t.toString())



            }
        })

        return mutableLiveData
    }


    fun requestPlayerData(
        apiKey: String,seriesId: String
    ): MutableLiveData<PlayerResponse> {
        val mutableLiveData: MutableLiveData<PlayerResponse> = MutableLiveData()


        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.getPlayerInfo(apiKey,seriesId)?.enqueue(object :
            Callback<PlayerResponse?> {
            override fun onResponse(call: Call<PlayerResponse?>?, response: Response<PlayerResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )

                    Log.d("response_raw", "onResponse: "+response.raw())



                }else{

                    Log.d("response_raw", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<PlayerResponse?>, t: Throwable?) {

                Log.d("response_raw", "onFailure: "+t.toString())



            }
        })

        return mutableLiveData
    }


    fun requestPlayerInfoData(
        apiKey: String,playerId: String
    ): MutableLiveData<PlayerInfoResponse> {
        val mutableLiveData: MutableLiveData<PlayerInfoResponse> = MutableLiveData()


        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.getPlayerInfoData(apiKey,playerId)?.enqueue(object :
            Callback<PlayerInfoResponse?> {
            override fun onResponse(call: Call<PlayerInfoResponse?>?, response: Response<PlayerInfoResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )

                    Log.d("response_raw", "onResponse: "+response.raw())



                }else{

                    Log.d("response_raw", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<PlayerInfoResponse?>, t: Throwable?) {

                Log.d("response_raw", "onFailure: "+t.toString())



            }
        })

        return mutableLiveData
    }

    fun requestPointsTableData(
        apiKey: String,seriesId: String
    ): MutableLiveData<PointsTableResponse> {
        val mutableLiveData: MutableLiveData<PointsTableResponse> = MutableLiveData()


        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.getPointsTableData(apiKey,seriesId)?.enqueue(object :
            Callback<PointsTableResponse?> {
            override fun onResponse(call: Call<PointsTableResponse?>?, response: Response<PointsTableResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )

                    Log.d("response_raw", "onResponse: "+response.raw())



                }else{

                    Log.d("response_raw", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<PointsTableResponse?>, t: Throwable?) {

                Log.d("response_raw", "onFailure: "+t.toString())



            }
        })

        return mutableLiveData
    }
}