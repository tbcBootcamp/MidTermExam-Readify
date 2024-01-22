package com.example.midtermexam.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.midtermexam.R
import com.example.midtermexam.data.global.model.BookItemDto
import com.example.midtermexam.data.local.model.StarEntity
import com.example.midtermexam.databinding.ItemBookBinding
import com.example.midtermexam.presentation.model.BookItemUiModel
import timber.log.Timber

class BooksPagingAdapter() :
    PagingDataAdapter<BookItemUiModel, BooksPagingAdapter.ViewHolder>(DiffUtils) {

    private var starDelete: ((String) -> (Unit))? = null
    fun setStarDelete(block: (String) -> Unit) {
        starDelete = block
    }

    private var starInsert: ((StarEntity) -> (Unit))? = null
    fun setStarInsert(block: (StarEntity) -> (Unit)) {
        starInsert = block
    }

    private var linkInfo: ((String) -> (Unit))? = null
    fun setLinkInfo(block: (String) -> Unit) {
        linkInfo = block
    }

    inner class ViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.bookLink.setOnClickListener {
                getItem(absoluteAdapterPosition)?.infoLink?.let { it1 ->
                    linkInfo?.invoke(it1)
                }
            }

            binding.starStatus.setOnClickListener {
                if (absoluteAdapterPosition != -1) {
                    val data = getItem(absoluteAdapterPosition)
                    if (data?.statusStar == true) {
                        data.statusStar = false
                        binding.starStatus.setImageResource(R.drawable.ic_star_border)
                        starDelete?.invoke(data.bookId)
                    } else {
                        data?.statusStar = true
                        binding.starStatus.setImageResource(R.drawable.ic_star_full)
                        data?.let {
                            starInsert?.invoke(
                                StarEntity(
                                    bookId = data.bookId ?: "",
                                    author = data.author ?: "",
                                    title = data.title ?: "",
                                    imageLink = data.imageLink,
                                    infoLink = data.infoLink
                                )
                            )
                        }
                    }
                } else {
                    Timber.tag("IDABSOLUTE").d(absoluteAdapterPosition.toString())
                }
            }
        }

        fun onBind() {
            getItem(absoluteAdapterPosition)?.let { item ->
//                if (!item.volumeInfo?.authors.isNullOrEmpty()) {
                binding.authorBook.text = item.author
//                }
                binding.nameBook.text = item.title
                Timber.tag("HHHH").d(item.imageLink ?: "")
                Glide.with(binding.root.context)
                    .load(item.imageLink)
                    .placeholder(R.drawable.ic_password).into(binding.imageBook)

                if (item.statusStar)
                    binding.starStatus.setImageResource(R.drawable.ic_star_full)
                else
                    binding.starStatus.setImageResource(R.drawable.ic_star_border)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    object DiffUtils : DiffUtil.ItemCallback<BookItemUiModel>() {
        override fun areItemsTheSame(
            oldBookItemDto: BookItemUiModel,
            newBookItemDto: BookItemUiModel
        ): Boolean =
            oldBookItemDto.bookId == newBookItemDto.bookId

        override fun areContentsTheSame(
            oldBookItemDto: BookItemUiModel,
            newBookItemDto: BookItemUiModel
        ): Boolean = oldBookItemDto == newBookItemDto

    }
}