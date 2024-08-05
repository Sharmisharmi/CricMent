package com.example.cricbuzz.match.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cricbuzz.api.APInterface
import com.example.cricbuzz.api.ApiClient
import com.example.cricbuzz.match.model.BallbyBallResponse
import com.example.cricbuzz.match.model.MatchInfoReponse
import com.example.cricbuzz.match.model.MatchesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchesRepository {


//    fun requestLiveMatchesDataList(
//        apiKey: String,
//        offset: Int,
//        live_recyclerView: RecyclerView,
//        no_data_availableLL: LinearLayout
//    ): MutableLiveData<MatchesResponse> {
//        val mutableLiveData: MutableLiveData<MatchesResponse> = MutableLiveData()
//
//
//        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
//        apiService?.getLiveMatchData(apiKey,offset)?.enqueue(object :
//            Callback<MatchesResponse?> {
//            override fun onResponse(call: Call<MatchesResponse?>?, response: Response<MatchesResponse?>) {
//                if (response.isSuccessful && response.body() != null) {
//                    mutableLiveData.setValue(response.body()!! )
//
//                    Log.d("response_raw", "onResponse: "+response.raw())
//                    live_recyclerView.visibility = View.VISIBLE
//                    no_data_availableLL.visibility = View.GONE
//
//
//
//                }else{
//                    live_recyclerView.visibility = View.GONE
//                    no_data_availableLL.visibility = View.VISIBLE
//                    Log.d("response_raw", "onResponse: "+response.raw())
//
//                }
//            }
//            public override fun onFailure(call: Call<MatchesResponse?>, t: Throwable?) {
//                live_recyclerView.visibility = View.GONE
//                no_data_availableLL.visibility = View.VISIBLE
//                Log.d("response_raw", "onResponse: "+call.request().body)
//
//
//
//            }
//        })
//
//        return mutableLiveData
//    }
//
//    fun requestUpcomingMatchesDataList(
//        apiKey: String,
//        live_recyclerView: RecyclerView,
//        no_data_availableLL: LinearLayout
//    ): MutableLiveData<MatchesResponse> {
//        val mutableLiveData: MutableLiveData<MatchesResponse> = MutableLiveData()
//
//
//        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
//        apiService?.getUpcomingMatchData(apiKey)?.enqueue(object :
//            Callback<MatchesResponse?> {
//            override fun onResponse(call: Call<MatchesResponse?>?, response: Response<MatchesResponse?>) {
//                if (response.isSuccessful && response.body() != null) {
//                    mutableLiveData.setValue(response.body()!! )
//
//                    Log.d("response_raw", "onResponse: "+response.raw())
//                    live_recyclerView.visibility = View.VISIBLE
//                    no_data_availableLL.visibility = View.GONE
//
//
//
//                }else{
//                    live_recyclerView.visibility = View.GONE
//                    no_data_availableLL.visibility = View.VISIBLE
//                    Log.d("response_raw", "onResponse: "+response.raw())
//
//                }
//            }
//            public override fun onFailure(call: Call<MatchesResponse?>, t: Throwable?) {
//                live_recyclerView.visibility = View.GONE
//                no_data_availableLL.visibility = View.VISIBLE
//                Log.d("response_raw", "onResponse: "+call.request().body)
//
//
//
//            }
//        })
//
//        return mutableLiveData
//    }


    fun requestFinishedMatchesDataList(
        apiKey: String,offset: Int
    ): MutableLiveData<MatchesResponse> {
        val mutableLiveData: MutableLiveData<MatchesResponse> = MutableLiveData()


        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.getFinishedMatchData(apiKey,offset)?.enqueue(object :
            Callback<MatchesResponse?> {
            override fun onResponse(call: Call<MatchesResponse?>?, response: Response<MatchesResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )

                    Log.d("response_raw", "onResponse: "+response.raw())



                }else{

                    Log.d("response_raw", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<MatchesResponse?>, t: Throwable?) {

                Log.d("response_raw", "onFailure: "+t.toString())



            }
        })

        return mutableLiveData
    }

    fun requestMatchesData(
        apiKey: String, match_id: String
    ): MutableLiveData<MatchInfoReponse> {
        val mutableLiveData: MutableLiveData<MatchInfoReponse> = MutableLiveData()


        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.getMatchInfoData(apiKey,match_id)?.enqueue(object :
            Callback<MatchInfoReponse?> {
            override fun onResponse(call: Call<MatchInfoReponse?>?, response: Response<MatchInfoReponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )

                    Log.d("response_raw", "onResponse: "+response.raw())



                }else{

                    Log.d("response_raw", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<MatchInfoReponse?>, t: Throwable?) {

                Log.d("response_raw", "onFailure: "+t.toString())



            }
        })

        return mutableLiveData
    }


    fun requestBBBData(
        apiKey: String, match_id: String
    ): MutableLiveData<BallbyBallResponse> {
        val mutableLiveData: MutableLiveData<BallbyBallResponse> = MutableLiveData()


        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.getBBBData(apiKey,match_id)?.enqueue(object :
            Callback<BallbyBallResponse?> {
            override fun onResponse(call: Call<BallbyBallResponse?>?, response: Response<BallbyBallResponse?>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )

                    Log.d("response_raw", "onResponse: "+response.raw())



                }else{

                    Log.d("response_raw", "onError: "+response.raw())

                }
            }
            public override fun onFailure(call: Call<BallbyBallResponse?>, t: Throwable?) {

                Log.d("response_raw", "onFailure: "+t.toString())



            }
        })

        return mutableLiveData
    }

//
//    fun requestFinishedMatchesData(
//        apiKey: String
//    ): MutableLiveData<MatchesResponse> {
//        val mutableLiveData: MutableLiveData<MatchesResponse> = MutableLiveData()
//
//
//        val apiService: APInterface? = ApiClient.getRetrofitClient()?.create(APInterface::class.java)
//        apiService?.getFinishedMatchData(apiKey)?.enqueue(object :
//            Callback<MatchesResponse?> {
//            override fun onResponse(call: Call<MatchesResponse?>?, response: Response<MatchesResponse?>) {
//                if (response.isSuccessful && response.body() != null) {
//                    mutableLiveData.setValue(response.body()!! )
//
//                    Log.d("response_raw", "onResponse: "+response.raw())
//
//
//
//
//                }else{
//
//                    Log.d("response_raw", "onError: "+response.raw())
//
//                }
//            }
//            public override fun onFailure(call: Call<MatchesResponse?>, t: Throwable?) {
//
//                Log.d("response_raw", "onFailure: "+t.toString())
//
//
//
//            }
//        })
//
//        return mutableLiveData
//    }
}