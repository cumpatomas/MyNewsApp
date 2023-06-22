package com.example.mynewsapp.data.model

import com.google.gson.annotations.SerializedName

data class JsonModel(
    /** This Array must have THE SAME NAME as the main array in JSON file OR use SERIALIZE */
    @SerializedName("articles")
    var articles: List<NewModel>?
)

data class NewModel(
//  val id: String = UUID.randomUUID().toString(),
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("publishedAt")
    val date: String,
    @SerializedName("url")
    val link: String,
    @SerializedName("urlToImage")
    val photo: String,
    @SerializedName("author")
    val source: String,
    )