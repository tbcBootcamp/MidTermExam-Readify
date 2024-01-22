package com.example.midtermexam.data.local.appDataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.midtermexam.data.local.dao.StarDao
import com.example.midtermexam.data.local.model.StarEntity

@Database(
    entities = [StarEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun starDao(): StarDao
}