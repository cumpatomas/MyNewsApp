package com.example.mynewsapp.ui.homefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapp.data.model.NewModel
import com.example.mynewsapp.domain.GetNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeFragmentViewModel: ViewModel() {
    private val getNews = GetNewsUseCase()

    private val _newsList = MutableStateFlow(listOf<NewModel>())
    val newsList = _newsList.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    init {
        _loading.value = true
        viewModelScope.launch {
            launch {
                _newsList.value = getNews.invoke()

            }.join()
            _loading.value = false
        }
    }
}