package com.example.cricbuzz.series.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cricbuzz.api.APInterface
import com.example.cricbuzz.api.ApiClient
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
}