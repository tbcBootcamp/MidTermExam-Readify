package com.example.midtermexam.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun logIn(email: String, password: String): Flow<String>
    suspend fun register(email:String, password: String): Flow<String>
    suspend fun logOut()
    suspend fun isLoggedIn(): Boolean
}