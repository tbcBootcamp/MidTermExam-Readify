package com.example.midtermexam.presentation.fragments.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midtermexam.domain.usecase.auth.RegistrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(private val registrationUseCase: RegistrationUseCase) :
    ViewModel() {

    val registrationResult = MutableSharedFlow<String>()

    fun register(email: String, password: String) {
        viewModelScope.launch {
            registrationUseCase(email, password).collect {
                registrationResult.emit(it)
            }
        }
    }
}