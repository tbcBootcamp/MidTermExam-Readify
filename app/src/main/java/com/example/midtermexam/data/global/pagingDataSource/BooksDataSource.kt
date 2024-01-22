package com.example.midtermexam.data.global.pagingDataSource

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.midtermexam.BuildConfig
import com.example.midtermexam.data.global.model.BookItemDto
import com.example.midtermexam.data.local.dao.StarDao
import com.example.midtermexam.domain.model.BookItemDomainModel
import com.example.midtermexam.domain.usecase.books.GetBooksUseCase
import com.example.midtermexam.domain.usecase.books.IsStarUseCase
import com.example.midtermexam.data.global.util.isOnline
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class BooksDataSource @Inject constructor(
    private val context: Context,
    private val getBooksUseCase: GetBooksUseCase,
    private val queryText: String,
    private val isStarUseCase: IsStarUseCase
) : PagingSource<Int, BookItemDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, BookItemDomainModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        Timber.tag("HHH").d("Refresh key")
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookItemDomainModel> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        Timber.tag("HHH").d("We start")

        if (isOnline(context)) {
            val response = getBooksUseCase(queryText, pageSize, page, BuildConfig.API_KEY)
            Timber.tag("HHH").d(response.toString())
//            return if (response.isSuccessful) {
            return if (response.isEmpty()) {
                LoadResult.Page(listOf(), null, null)
            } else {
                Timber.tag("HHH").d("posts $response")
                response.forEach { item ->
                    val job = CoroutineScope(Dispatchers.IO).launch {
                        isStarUseCase(item.bookId).onEach {
                            if (it.isNotEmpty()) item.statusStar = true
                        }.collect {}
                    }
                    job.onJoin
                }
                val nextKey = if (response.size < pageSize) null else page + 1
                val prevKey = if (page == 1) null else page - 1
                LoadResult.Page(response, prevKey, nextKey)
            }
//            } else {
//                LoadResult.Error(Throwable("Error with Connection"))
//            }
        } else {
            return LoadResult.Page(listOf(), null, null)
        }
    }
}