package com.example.midtermexam.data.repository

import com.example.midtermexam.data.global.model.BooksDto
import com.example.midtermexam.data.global.model.toDomain
import com.example.midtermexam.data.global.service.BooksApi
import com.example.midtermexam.data.local.dao.StarDao
import com.example.midtermexam.domain.model.BookItemDomainModel
import com.example.midtermexam.domain.repository.BooksRepository
import retrofit2.Response
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val booksApi: BooksApi
) : BooksRepository {
    override suspend fun getBooks(
        query: String,
        maxResults: Int,
        startIndex: Int,
        apiKey: String
    ): List<BookItemDomainModel> {
        val response = booksApi.getBooks(query, maxResults, startIndex, apiKey)
        return if (response.isSuccessful)
            response.body()!!.items.map { it.toDomain() }
        else emptyList()
    }
}
