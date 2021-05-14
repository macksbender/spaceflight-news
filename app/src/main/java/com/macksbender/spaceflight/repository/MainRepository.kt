package com.macksbender.spaceflight.repository

import com.macksbender.spaceflight.data.remote.SpaceFlightAPI
import com.macksbender.spaceflight.data.remote.responses.Article
import com.macksbender.spaceflight.data.remote.responses.ArticleList
import com.macksbender.spaceflight.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MainRepository @Inject constructor(
    private val api: SpaceFlightAPI
) {

    suspend fun getArticleList(limit: Int, offset: Int): Resource<ArticleList> {
        val response = try {
            api.getArticleList(limit, offset)
        } catch (throwable: Throwable) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }

    suspend fun getArticleInfo(id: String): Resource<Article> {
        val response = try {
            api.getArticleInfo(id)
        } catch (throwable: Throwable) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }
}