package com.example.challenge2.data


import retrofit2.Call
import retrofit2.http.*

interface KasusService {

    @GET("/")
    fun getRegion(): Call<List<KasusGlobalItem>>

    @GET("/indonesia/provinsi")
    fun getProvinsi(): Call<List<ProvinsiItem>>

    @GET("/indonesia")
    fun getIndo(): Call<List<IndoItem>>

}