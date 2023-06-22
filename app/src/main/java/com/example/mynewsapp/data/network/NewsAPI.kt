package com.example.mynewsapp.data.network

import com.example.mynewsapp.data.model.JsonModel
import com.example.mynewsapp.data.model.NewModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface NewsAPI {

//    @Headers("x-api-key: XmBgQ5d67gtwKRu540XJTOPdysPMjI-WAVRHGhN2goY")
//    @Headers("x-api-key: e2d66783ec0a4cb095d247c649323059")
    @GET
    suspend fun getRecipes(
        @Url url: String
    ): Response<JsonModel>
}