package com.example.midtermexam.domain.usecase.auth

import com.example.midtermexam.domain.repository.AuthRepository
import javax.inject.Inject


class isUserLoggedInUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): Boolean {
        return authRepository.isLoggedIn()
    }
}