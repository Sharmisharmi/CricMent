package com.example.cricbuzz.api

import com.example.cricbuzz.login.LoginData
import com.example.cricbuzz.login.LoginResponse
import com.example.cricbuzz.match.model.MatchesResponse
import com.example.cricbuzz.series.model.SeriesResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File


interface APInterface {


    // Login


    @FormUrlEncoded
    @POST("LoginProfile")
    fun uploadFormData(
        @Field("image") field11: File?,
        @Field("name") field1: String,
        @Field("email") field2: String
    ): Call<LoginResponse>


    @Headers("Content-Type: application/json")
    @POST("LoginProfile")
    fun generalLogin(@Body Postdata: String?): Call<LoginResponse?>?

    @Multipart
    @POST("/api/change-profile")
    fun editProfile(
        @Part("phoneNumber") rPhoneNumber: RequestBody?,
        @Part("oldPassword") rPassword: RequestBody?,
        @Part rProfilePicture: Part?
    ): Call<LoginData>?

//  CricAPI


    // Matches

    @GET("v1/matches")
    fun getFinishedMatchData(@Query("apikey") apiKey:String, @Query("offset") offset: Int): Call<MatchesResponse?>?



    // Series


    @GET("v1/series")
    fun getSeriesList(@Query("apikey") apiKey:String, @Query("offset") offset: Int): Call<SeriesResponse?>?



//    @GET("v1/currentMatches")
//    fun getLiveMatchData(@Query("apikey") apiKey:String, @Query("offset") userId: Int): Call<LiveMatchesResponse?>?
//
//
//
//    @GET("v1/cricScore")
//    fun getUpcomingMatchData(@Query("apikey") apiKey:String): Call<UpcomingMatchesResponse?>?
//
//
//
//    @GET("matches/49689/live")
//    fun getFinishedMatchData(@Query("token") apiKey: String): Call<CompletedMatchesResponse?>?





}