package com.example.cricbuzz.login

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.File

class LoginViewModel{


    private var loginRepository:LoginRepository? = null


    private var generalsignUp: MutableLiveData<LoginResponse> = MutableLiveData()
    fun generalLogin(json: String,activity: Activity): MutableLiveData<LoginResponse> {
        generalsignUp = loginRepository!!.generalLogin(json,activity)
        return generalsignUp
    }


    init {
        this.loginRepository = LoginRepository()
    }
}