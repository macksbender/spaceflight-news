package com.macksbender.spaceflight.data.remote.responses

data class Article(
    val events: List<Event>,
    val featured: Boolean,
    val id: String,
    val imageUrl: String,
    val launches: List<Launch>,
    val newsSite: String,
    val publishedAt: String,
    val summary: String,
    val title: String,
    val url: String
)