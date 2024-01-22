package com.example.midtermexam.domain.repository

import com.example.midtermexam.domain.model.BookItemDomainModel

interface BooksRepository {
    suspend fun getBooks(
        query: String,
        maxResults: Int,
        startIndex: Int,
        apiKey: String
    ): List<BookItemDomainModel>
}