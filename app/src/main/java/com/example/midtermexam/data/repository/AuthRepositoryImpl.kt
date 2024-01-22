package com.example.midtermexam.data.repository

import com.example.midtermexam.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {
    override suspend fun logIn(email: String, password: String): Flow<String> = flow {
        try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if (user != null) {
                emit("Login successful for user: ${user.email}")
            } else {
                emit("Login failed. User not found.")
            }
        } catch (e: Exception) {
            emit("Login failed: ${e.message}")
        }
    }

    override suspend fun register(email: String, password: String): Flow<String> = flow {
        try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if (user != null) {
                emit("Registration successful for user: ${user.email}")
            } else {
                emit("Registration failed. User not created.")
            }
        } catch (e: Exception) {
            emit("Registration failed: ${e.message}")
        }
    }

    override suspend fun logOut() {
        auth.signOut()
    }

    override suspend fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

}