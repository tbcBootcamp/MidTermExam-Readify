package com.example.midtermexam.domain.usecase.books

import com.example.midtermexam.domain.model.BookItemDomainModel
import com.example.midtermexam.domain.repository.BooksRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(private val booksRepository: BooksRepository) {
    suspend operator fun invoke(
        searchText: String, maxResults: Int,
        startIndex: Int, apiKey: String
    ): List<BookItemDomainModel> {
        return booksRepository.getBooks(searchText, maxResults, startIndex, apiKey)
    }
}

