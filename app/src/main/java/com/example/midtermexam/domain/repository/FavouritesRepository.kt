package com.example.midtermexam.domain.repository

import com.example.midtermexam.domain.model.BookItemDomainModel
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    suspend fun insertStar(star: BookItemDomainModel)
    suspend fun deleteStar(bookId: String)
    fun isStar(bookId: String): Flow<List<BookItemDomainModel>>
    fun allStar(): Flow<List<BookItemDomainModel>>
}