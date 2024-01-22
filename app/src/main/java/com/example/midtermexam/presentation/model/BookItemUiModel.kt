package com.example.midtermexam.presentation.model

import com.example.midtermexam.domain.model.BookItemDomainModel

data class BookItemUiModel(
    val bookId: String,
    val author: String,
    val title: String?,
    val imageLink: String?,
    val infoLink: String?,
    var statusStar: Boolean
)

fun BookItemDomainModel.toUi() = BookItemUiModel(
    bookId = bookId,
    author = author,
    title = title,
    imageLink = imageLink,
    infoLink = infoLink,
    statusStar = statusStar
)