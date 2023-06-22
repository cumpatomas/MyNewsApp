package com.example.mynewsapp.domain

import com.example.mynewsapp.data.model.NewModel
import com.example.mynewsapp.data.network.NewsService
import com.example.mynewsapp.data.network.ResponseEvent

class GetNewsUseCase {
    private val provider = NewsService()
    private val newsList = mutableListOf<NewModel>()
    private var idCount = 0

    suspend operator fun invoke(): List<NewModel> {
        when (val result = provider.getNews()) {
            is ResponseEvent.Error -> {
                print("ERROR!")
                result.exception
            }

            is ResponseEvent.Success -> {
                val jsonDataList = result.data.articles

                if (jsonDataList != null) {
                    for (i in 0 until jsonDataList.count()) {
                        newsList.add(
                            NewModel(
                                id = idCount,
                                title = jsonDataList[i].title,
                                date = jsonDataList[i].date,
                                link = jsonDataList[i].link,
                                photo = jsonDataList[i].photo,
                                source = jsonDataList[i].source
                            )
                        )
                        idCount++
                    }
                }
            }
        }
        println("LIST: $newsList")
        return newsList
    }
}