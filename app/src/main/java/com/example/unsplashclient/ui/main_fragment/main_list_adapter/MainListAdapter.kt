package com.example.unsplashclient.ui.main_fragment.main_list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import com.example.unsplashclient.BR
import com.example.unsplashclient.R
import com.example.unsplashclient.databinding.ImagePreviewCardViewBinding
import com.example.unsplashclient.databinding.QuickSearchButtonBinding
import com.example.unsplashclient.databinding.QuickSearchButtonShimmerBinding

class MainListAdapter() :
    PagingDataAdapter<MainListItemData, MainListItemViewHolder<*>>(MainListItemComparator()) {

    companion object {
        private const val EMPTY_ITEM = 0
        private const val IMAGE_PREVIEW = 1
        private const val QUICK_SEARCH = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is EmptyImagePreviewData -> EMPTY_ITEM
            is ImagePreviewData -> IMAGE_PREVIEW
            is QuickSearchData -> QUICK_SEARCH
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainListItemViewHolder<*> {

        return when (viewType) {
            EMPTY_ITEM -> EmptyHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.image_preview_card_view_shimmer,
                    parent,
                    false
                ),
            )

            IMAGE_PREVIEW -> {
                ImagePreviewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.image_preview_card_view,
                        parent,
                        false
                    ),
                )
            }

            QUICK_SEARCH -> {
                QuickSearchHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.quick_search_button,
                        parent,
                        false
                    ),
                )
            }

            else -> throw IllegalArgumentException("Invalid type of data")
        }
    }

    override fun onBindViewHolder(holder: MainListItemViewHolder<*>, position: Int) {
        when (val item = getItem(position)) {
            is EmptyImagePreviewData -> (holder as? EmptyHolder)?.bind(item)
            is ImagePreviewData -> (holder as? ImagePreviewHolder)?.bind(item)
            is QuickSearchData -> (holder as? QuickSearchHolder)?.bind(item)
            else -> return
        }
    }

    inner class ImagePreviewHolder(
        var binding: ImagePreviewCardViewBinding
    ) : MainListItemViewHolder<ImagePreviewData>(binding.root) {
        override fun bind(data: ImagePreviewData) {
            binding.setVariable(BR.dataModel, data)
            binding.executePendingBindings()
        }
    }

    inner class EmptyHolder(
        var binding: ViewDataBinding
    ) : MainListItemViewHolder<EmptyImagePreviewData>(binding.root) {
        override fun bind(data: EmptyImagePreviewData) {
            binding.executePendingBindings()
        }
    }

    inner class QuickSearchHolder(
        var binding: QuickSearchButtonBinding
    ) : MainListItemViewHolder<QuickSearchData>(binding.root) {
        override fun bind(data: QuickSearchData) {
            binding.executePendingBindings()
        }
    }
}