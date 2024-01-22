package com.example.midtermexam.data.global.service

import com.example.midtermexam.data.global.model.BooksDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {
    @GET("/books/v1/volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int,
        @Query("startIndex") startIndex: Int,
        @Query("key") apiKey: String
    ): Response<BooksDto>
}