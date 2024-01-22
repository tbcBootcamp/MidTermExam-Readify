package com.example.midtermexam.presentation.fragments.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midtermexam.data.local.model.StarEntity
import com.example.midtermexam.data.local.model.toDomainModel
import com.example.midtermexam.domain.usecase.books.DeleteStarUseCase
import com.example.midtermexam.domain.usecase.books.GetFavouritesUseCase
import com.example.midtermexam.domain.usecase.books.InsertStarUseCase
import com.example.midtermexam.presentation.model.BookItemUiModel
import com.example.midtermexam.presentation.model.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavouritesUseCase,
    private val deleteStarUseCase: DeleteStarUseCase,
    private val insertStarUseCase: InsertStarUseCase
) : ViewModel() {
    suspend fun getFavorites(): Flow<List<BookItemUiModel>> {
        return getFavouritesUseCase().map { it.map { domainModel -> domainModel.toUi() } }
    }

    fun deleteStarFromDb(id: String) {
        viewModelScope.launch {
            deleteStarUseCase(id)
        }
    }

    fun insertStarToDb(starEntity: StarEntity) {
        viewModelScope.launch {
            insertStarUseCase(starEntity.toDomainModel())
        }
    }
}