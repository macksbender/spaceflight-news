package com.macksbender.spaceflight.data.remote

import com.macksbender.spaceflight.data.remote.responses.Article
import com.macksbender.spaceflight.data.remote.responses.ArticleList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpaceFlightAPI {

    @GET("articles")
    suspend fun getArticleList(
        @Query("_limit") limit: Int,
        @Query("_start") offset: Int
    ): ArticleList

    @GET("articles/{id}")
    suspend fun getArticleInfo(
        @Path("id") id: String
    ): Article

}