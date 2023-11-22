package com.example.cricbuzz.login

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cricbuzz.api.APInterface
import com.example.cricbuzz.api.LoginApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class LoginRepository {


    fun generalLogin(json: String,activity: Activity): MutableLiveData<LoginResponse> {
        val mutableLiveData: MutableLiveData<LoginResponse> = MutableLiveData()
        val apiService: APInterface? =
            LoginApiClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.generalLogin(json)?.enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>?,
                response: Response<LoginResponse?>
            ) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("response_otp", "onResponse: " + response.raw())
                    mutableLiveData.setValue(response.body()!!)
                } else {
                    Log.i("Errorass", response.toString())
                }
            }

            public override fun onFailure(call: Call<LoginResponse?>, t: Throwable?) {
                Log.i("Error", "" + call.request().toString().trim() + " " + t)
            }
        })
        return mutableLiveData
    }
}