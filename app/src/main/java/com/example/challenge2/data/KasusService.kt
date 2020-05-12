package com.example.challenge2.data

import retrofit2.Call
import retrofit2.http.GET

interface KasusService {

    @GET("api")
    fun getGlobal(): Call<List<DataKasusItem>>
}