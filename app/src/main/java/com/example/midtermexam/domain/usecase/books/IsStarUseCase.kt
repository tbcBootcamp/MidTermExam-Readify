package com.example.midtermexam.domain.usecase.books

import com.example.midtermexam.domain.model.BookItemDomainModel
import com.example.midtermexam.domain.repository.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsStarUseCase @Inject constructor(private val favouritesRepository: FavouritesRepository) {
    suspend operator fun invoke(bookId: String): Flow<List<BookItemDomainModel>> {
        return favouritesRepository.isStar(bookId)
    }
}