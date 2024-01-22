package com.example.midtermexam.domain.usecase.auth

import com.example.midtermexam.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(private val authRepository: AuthRepository){
    suspend operator fun invoke(email:String, password:String): Flow<String>{
        return authRepository.register(email, password)
    }
}