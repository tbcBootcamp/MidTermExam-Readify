package com.example.midtermexam.data.repository

import com.example.midtermexam.data.local.dao.StarDao
import com.example.midtermexam.data.local.model.toDomainModel
import com.example.midtermexam.domain.model.BookItemDomainModel
import com.example.midtermexam.domain.model.toEntity
import com.example.midtermexam.domain.repository.FavouritesRepository
import com.example.midtermexam.presentation.model.BookItemUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(
    private val dao: StarDao
) : FavouritesRepository {
    override suspend fun insertStar(star: BookItemDomainModel) {
        dao.insertStar(star.toEntity())
    }

    override suspend fun deleteStar(bookId: String) {
        dao.deleteStar(bookId)
    }

    override fun isStar(bookId: String): Flow<List<BookItemDomainModel>> {
        return dao.isStar(bookId).map { it.map { entity -> entity.toDomainModel() } }
    }

    override fun allStar(): Flow<List<BookItemDomainModel>> {
        return dao.allStar().map { it.map { entity -> entity.toDomainModel() } }
    }
}