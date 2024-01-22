package com.example.midtermexam.presentation.fragments.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midtermexam.domain.usecase.auth.LogOutUseCase
import com.example.midtermexam.domain.usecase.auth.isUserLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    private val isUserLoggedInUseCase: isUserLoggedInUseCase
) : ViewModel() {

    val logOut = MutableSharedFlow<Boolean>()

    fun logOut() {
        viewModelScope.launch {
            logOutUseCase()
        }.invokeOnCompletion {
            viewModelScope.launch {
                logOut.emit(!isUserLoggedInUseCase())
            }
        }
    }


}