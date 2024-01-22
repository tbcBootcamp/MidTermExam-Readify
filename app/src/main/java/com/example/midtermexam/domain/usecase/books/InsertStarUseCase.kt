package com.example.midtermexam.domain.usecase.books

import com.example.midtermexam.domain.model.BookItemDomainModel
import com.example.midtermexam.domain.repository.FavouritesRepository
import javax.inject.Inject

class InsertStarUseCase @Inject constructor(private val favouritesRepository: FavouritesRepository) {
    suspend operator fun invoke(star: BookItemDomainModel) {
        return favouritesRepository.insertStar(star)
    }
}