package com.example.mynewsapp.data.network

import com.example.mynewsapp.data.model.JsonModel
import com.example.mynewsapp.manualdi.NewsModule

class NewsService {

    private val retrofit = NewsModule.retrofit.create(NewsAPI::class.java)
    suspend fun getNews(): ResponseEvent<JsonModel> {
        return try {
            val response = retrofit.getRecipes("top-headlines?sources=bbc-news&apiKey=e2d66783ec0a4cb095d247c649323059")
//            val response = retrofit.getRecipes("top-headlines?sources=abc-news&apiKey=e2d66783ec0a4cb095d247c649323059")
            if (response.isSuccessful) {
                response.body()?.let { jsonData ->

                    ResponseEvent.Success(jsonData)
                } ?: run {
                    ResponseEvent.Error(Exception("Response body is null."))
                }
            } else {
                ResponseEvent.Error(Exception("Get recipes not successfull."))

            }
        } catch (e: Exception) {
            ResponseEvent.Error(e)
        }
    }
}