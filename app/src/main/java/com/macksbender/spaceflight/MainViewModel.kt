package com.macksbender.spaceflight

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macksbender.spaceflight.data.remote.responses.Article
import com.macksbender.spaceflight.data.remote.responses.ArticleList
import com.macksbender.spaceflight.repository.MainRepository
import com.macksbender.spaceflight.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    var newsList = mutableStateOf<List<Article>>(listOf())
    var isLoading = mutableStateOf(false)
    var lastError = mutableStateOf("")

    init {
        loadNewsList()
    }

    fun loadNewsList() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getArticleList(5, 0)
            when(result) {
                is Resource.Success -> {
                    val articleList = result.data?.toList()
                    if (articleList != null) newsList.value = articleList
                    isLoading.value = false
                    lastError.value = ""
                }
                is Resource.Error -> {
                    isLoading.value = false
                    lastError.value = result.msg.toString()
                    println(lastError)
                }
            }
        }
    }

}