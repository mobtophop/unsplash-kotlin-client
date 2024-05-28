package com.example.unsplashclient.ui.main_fragment.quick_search_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.unsplashclient.databinding.QuickSearchButtonBinding
import com.example.unsplashclient.BR
import com.example.unsplashclient.R


class QuickSearchAdapter() :
    ListAdapter<BaseQuickSearchData, QuickSearchBaseViewHolder<*>>(QuickSearchComparator()) {

    companion object {
        private const val EMPTY_ITEM = 0
        private const val QUICK_SEARCH = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is EmptyQuickSearchData -> EMPTY_ITEM
            is QuickSearchData -> QUICK_SEARCH
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuickSearchBaseViewHolder<*> {

        return when (viewType) {
            EMPTY_ITEM -> EmptyHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.quick_search_button_shimmer,
                    parent,
                    false
                ),
            )

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

    override fun onBindViewHolder(holder: QuickSearchBaseViewHolder<*>, position: Int) {
        when (val item = getItem(position)) {
            is EmptyQuickSearchData -> (holder as? EmptyHolder)?.bind(item)
            is QuickSearchData -> (holder as? QuickSearchHolder)?.bind(item)
        }
    }

    inner class QuickSearchHolder(
        var binding: QuickSearchButtonBinding
    ) : QuickSearchBaseViewHolder<QuickSearchData>(binding.root) {
        override fun bind(data: QuickSearchData) {
            binding.setVariable(BR.view, binding.root)
            binding.executePendingBindings()
        }
    }

    inner class EmptyHolder(
        var binding: ViewDataBinding
    ) : QuickSearchBaseViewHolder<EmptyQuickSearchData>(binding.root) {
        override fun bind(data: EmptyQuickSearchData) {
            binding.setVariable(BR.view, binding.root)
            binding.executePendingBindings()
        }
    }
}