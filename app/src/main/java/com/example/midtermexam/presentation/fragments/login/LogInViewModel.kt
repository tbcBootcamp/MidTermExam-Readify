package com.example.midtermexam.presentation.fragments.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midtermexam.domain.usecase.auth.LogInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val logInUseCase: LogInUseCase) : ViewModel() {

    val logInResult = MutableSharedFlow<String>()

    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            logInUseCase(email, password).collect() {
                logInResult.emit(it)
            }
        }
    }
}