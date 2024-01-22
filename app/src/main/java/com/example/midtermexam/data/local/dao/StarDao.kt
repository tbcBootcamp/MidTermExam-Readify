package com.example.midtermexam.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.midtermexam.data.local.model.StarEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StarDao {
    @Insert
    suspend fun insertStar(start: StarEntity)

    @Query("DELETE FROM StarEntity WHERE StarEntity.bookId = :bookId")
    suspend fun deleteStar(bookId: String)

    @Query("SELECT *FROM StarEntity WHERE StarEntity.bookId = :bookId")
    fun isStar(bookId: String): Flow<List<StarEntity>>

    @Query("SELECT * FROM StarEntity")
    fun allStar(): Flow<List<StarEntity>>
}