package com.example.unsplashclient.ui.main_fragment.image_preview_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.unsplashclient.BR
import com.example.unsplashclient.R
import com.example.unsplashclient.databinding.ImagePreviewCardViewBinding

class ImagePreviewAdapter() :
    ListAdapter<BaseImagePreviewData, ImagePreviewBaseViewHolder<*>>(ImagePreviewComparator()) {

    companion object {
        private const val EMPTY_ITEM = 0
        private const val IMAGE_PREVIEW = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is EmptyImagePreviewData -> EMPTY_ITEM
            is ImagePreviewData -> IMAGE_PREVIEW
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagePreviewBaseViewHolder<*> {

        return when (viewType) {
            EMPTY_ITEM -> EmptyHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.image_preview_card_view_shimmer, parent, false
                ),
            )

            IMAGE_PREVIEW -> {
                ImagePreviewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.image_preview_card_view, parent, false
                    ),
                )
            }

            else -> throw IllegalArgumentException("Invalid type of data")
        }
    }

    override fun onBindViewHolder(holder: ImagePreviewBaseViewHolder<*>, position: Int) {
        when (val item = getItem(position)) {
            is EmptyImagePreviewData -> (holder as? EmptyHolder)?.bind(item)
            is ImagePreviewData -> (holder as? ImagePreviewHolder)?.bind(item)
        }
    }

    inner class ImagePreviewHolder(
        var binding: ImagePreviewCardViewBinding
    ) : ImagePreviewBaseViewHolder<ImagePreviewData>(binding.root) {
        override fun bind(data: ImagePreviewData) {
            binding.setVariable(BR.view, binding.root)
            binding.executePendingBindings()
        }
    }

    inner class EmptyHolder(
        var binding: ViewDataBinding
    ) : ImagePreviewBaseViewHolder<EmptyImagePreviewData>(binding.root) {
        override fun bind(data: EmptyImagePreviewData) {
            binding.setVariable(BR.view, binding.root)
            binding.executePendingBindings()
        }
    }
}