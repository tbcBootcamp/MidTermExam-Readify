package com.example.midtermexam.presentation.fragments.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.midtermexam.data.global.model.BookItemDto
import com.example.midtermexam.data.global.pagingDataSource.BooksDataSource
import com.example.midtermexam.data.local.dao.StarDao
import com.example.midtermexam.data.local.model.StarEntity
import com.example.midtermexam.data.local.model.toDomainModel
import com.example.midtermexam.domain.model.BookItemDomainModel
import com.example.midtermexam.domain.usecase.books.DeleteStarUseCase
import com.example.midtermexam.domain.usecase.books.GetBooksUseCase
import com.example.midtermexam.domain.usecase.books.InsertStarUseCase
import com.example.midtermexam.domain.usecase.books.IsStarUseCase
import com.example.midtermexam.presentation.model.BookItemUiModel
import com.example.midtermexam.presentation.model.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val deleteStarUseCase: DeleteStarUseCase,
    private val insertStarUseCase: InsertStarUseCase,
    private val isStarUseCase: IsStarUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ViewState())
    val state = _state.asStateFlow()

    fun requestSearch(text: String, context: Context): Flow<PagingData<BookItemUiModel>> {
        val query = text.ifEmpty { "IT" }
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                BooksDataSource(
                    context,
                    getBooksUseCase,
                    query,
                    isStarUseCase
                )
            }
        ).flow.cachedIn(viewModelScope)
            .map { it.map { bookItemDomainModel -> bookItemDomainModel.toUi() } }
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


    data class ViewState(
        val isLoading: Boolean = false
    )
}