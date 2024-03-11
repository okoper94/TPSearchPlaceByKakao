package com.kys2024.tpsearchplacebykakao.network

import android.telecom.Call
import com.kys2024.tpsearchplacebykakao.data.KakaoSearchPlaceResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {

    //카카오 로컬 검색 api...요청해주는 코드 만들어줘..우선 응답 tupe: String
    @Headers("Authorization: KakaoAK 21cc919cb0a6ac8bf7c3028f8a4ee080")
    @GET("/v2/local/search/keyword.json")
    fun searchPlaceToString(@Query("query") query:String ,@Query("x") longitude:String,@Query("y") latitude:String):retrofit2.Call<String>

    //카카오 로컬 검색 api...요청해주는 코드 만들어줘..우선 응답 tupe: KakaoSearchPlaceResponse
    @Headers("Authorization: KakaoAK 21cc919cb0a6ac8bf7c3028f8a4ee080")
    @GET("/v2/local/search/keyword.json?sort=distance")
    fun searchPlace(@Query("query") query:String ,@Query("x") longitude:String,@Query("y") latitude:String):retrofit2.Call<KakaoSearchPlaceResponse>

    //s네아로
    @GET("/v1/nid/me")
    fun  getNidUserInfo(@Header("Authorization") authorization:String ):retrofit2.Call <String>

}