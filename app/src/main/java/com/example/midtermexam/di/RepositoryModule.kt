package com.example.midtermexam.di

import com.example.midtermexam.data.repository.AuthRepositoryImpl
import com.example.midtermexam.data.repository.BooksRepositoryImpl
import com.example.midtermexam.data.repository.FavouritesRepositoryImpl
import com.example.midtermexam.domain.repository.AuthRepository
import com.example.midtermexam.domain.repository.BooksRepository
import com.example.midtermexam.domain.repository.FavouritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindBooksRepository(booksRepositoryImpl: BooksRepositoryImpl): BooksRepository

    @Binds
    @Singleton
    fun bindFavoritesRepository(favouritesRepositoryImpl: FavouritesRepositoryImpl): FavouritesRepository
}