package com.example.midtermexam.data.global.model

data class BooksDto(
    val items: List<BookItemDto>,
    val kind: String,
    val totalItems: Int
)

