package com.example.midtermexam.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.midtermexam.domain.model.BookItemDomainModel


@Entity
data class StarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bookId: String,
    val author: String,
    val title: String?,
    val imageLink: String?,
    val infoLink: String?
)

fun StarEntity.toDomainModel() = BookItemDomainModel(
    bookId, author, title, imageLink, infoLink, statusStar = true
)


