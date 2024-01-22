package com.example.midtermexam.domain.model

import com.example.midtermexam.data.local.model.StarEntity


data class BookItemDomainModel(
    val bookId: String,
    val author: String,
    val title: String?,
    val imageLink: String?,
    val infoLink: String?,
    var statusStar: Boolean = false
)

fun BookItemDomainModel.toEntity() = StarEntity(
    bookId = bookId,
    author = author,
    title = title,
    imageLink = imageLink,
    infoLink = infoLink
)