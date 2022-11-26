package com.maxmesh.testapp.data.network

import com.maxmesh.testapp.utils.Utils.BASE_URL
import com.maxmesh.testapp.utils.Utils.END_POINT
import com.maxmesh.testapp.domain.entity.MultimediaEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

val apiServices: ApiService? = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build().create(ApiService::class.java)

interface ApiService {
    @GET(END_POINT)
    suspend fun getDataFromAPI(): List<MultimediaEntity>
}