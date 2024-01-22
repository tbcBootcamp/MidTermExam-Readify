package com.example.midtermexam.data.global.model

import com.example.midtermexam.domain.model.BookItemDomainModel

data class BookItemDto(
    val accessInfo: AccessInfo,
    val etag: String,
    val id: String,
    val kind: String,
    val saleInfo: SaleInfo,
    val searchInfo: SearchInfo,
    val selfLink: String,
    val volumeInfo: VolumeInfo?,
    var statusStar: Boolean = false
)

fun BookItemDto.toDomain() = BookItemDomainModel(
    bookId = id,
    author = volumeInfo?.authors?.joinToString(". ") ?: "",
    title = volumeInfo?.title,
    imageLink = volumeInfo?.imageLinks?.smallThumbnail,
    infoLink = volumeInfo?.infoLink
)