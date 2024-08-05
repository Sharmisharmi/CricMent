package com.example.cricbuzz.api

import com.example.cricbuzz.home.model.CFResponse
import com.example.cricbuzz.home.model.InternationalSportsNewsResponse
import com.example.cricbuzz.home.model.UFFResponse
import com.example.cricbuzz.login.LoginData
import com.example.cricbuzz.login.LoginResponse
import com.example.cricbuzz.match.model.BallbyBallResponse
import com.example.cricbuzz.match.model.MatchInfoReponse
import com.example.cricbuzz.match.model.MatchesResponse
import com.example.cricbuzz.players.model.PlayerInfoResponse
import com.example.cricbuzz.players.model.PlayerResponse
import com.example.cricbuzz.series.model.PointsTableResponse
import com.example.cricbuzz.series.model.SeriesInfoRespnse
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
    @GET("v1/match_info")
    fun getMatchInfoData(@Query("apikey") apiKey:String, @Query("id") match_id: String): Call<MatchInfoReponse?>?


    @GET("v1/match_bbb")
    fun getBBBData(@Query("apikey") apiKey:String, @Query("id") match_id: String): Call<BallbyBallResponse?>?



    // Series


    @GET("v1/series")
    fun getSeriesList(@Query("apikey") apiKey:String, @Query("offset") offset: Int): Call<SeriesResponse?>?

    @GET("v1/series_info")
    fun getSeriesInfo(@Query("apikey") apiKey:String, @Query("id") id: String): Call<SeriesInfoRespnse?>?



    @GET("v1/series_squad")
    fun getPlayerInfo(@Query("apikey") apiKey:String, @Query("id") id: String): Call<PlayerResponse?>?


    @GET("v1/players_info")
    fun getPlayerInfoData(@Query("apikey") apiKey:String, @Query("id") id: String): Call<PlayerInfoResponse?>?

    @GET("v1/series_points")
    fun getPointsTableData(@Query("apikey") apiKey:String, @Query("id") id: String): Call<PointsTableResponse?>?



    @GET("v1/currentMatches")
    fun getCFMatchInfo(@Query("apikey") apiKey:String, @Query("offset") offset: Int): Call<CFResponse?>?


    @GET("v2/top-headlines")
    fun getSportsNewsList(@Query("apikey") apiKey:String, @Query("country") country: String, @Query("category") category: String): Call<InternationalSportsNewsResponse?>?


//    @GET("v2/top-headlines")
//    fun getSportsNewsList(@Query("apikey") apiKey:String, @Query("category") category: String): Call<InternationalSportsNewsResponse?>?
//
//
//
    @GET("v1/cricScore")
    fun getUFMatchInfo(@Query("apikey") apiKey:String): Call<UFFResponse?>?
//
//
//
//    @GET("matches/49689/live")
//    fun getFinishedMatchData(@Query("token") apiKey: String): Call<CompletedMatchesResponse?>?





}