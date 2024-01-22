package com.example.midtermexam.domain.usecase.books

import com.example.midtermexam.domain.repository.FavouritesRepository
import javax.inject.Inject

class DeleteStarUseCase @Inject constructor(private val favouritesRepository: FavouritesRepository) {
    suspend operator fun invoke(bookId: String) {
        return favouritesRepository.deleteStar(bookId)
    }
}