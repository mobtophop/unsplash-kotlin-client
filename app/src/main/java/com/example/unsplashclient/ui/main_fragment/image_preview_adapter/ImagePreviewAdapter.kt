package com.example.unsplashclient.ui.main_fragment.image_preview_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import com.example.unsplashclient.BR
import com.example.unsplashclient.R
import com.example.unsplashclient.databinding.ImagePreviewCardViewBinding

class ImagePreviewAdapter() :
    PagingDataAdapter<ImagePreviewData, ImagePreviewViewHolder<*>>(ImagePreviewComparator()) {

    companion object {
        private const val IMAGE_PREVIEW = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ImagePreviewData -> IMAGE_PREVIEW
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagePreviewViewHolder<*> {

        return when (viewType) {
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

            else -> throw IllegalArgumentException("Invalid type of data")
        }
    }

    override fun onBindViewHolder(holder: ImagePreviewViewHolder<*>, position: Int) {
        when (val item = getItem(position)) {
            is ImagePreviewData -> (holder as? ImagePreviewHolder)?.bind(item)
            else -> return
        }
    }

    inner class ImagePreviewHolder(
        var binding: ImagePreviewCardViewBinding
    ) : ImagePreviewViewHolder<ImagePreviewData>(binding.root) {
        override fun bind(data: ImagePreviewData) {
            binding.setVariable(BR.dataModel, data)
            binding.executePendingBindings()
        }
    }
}