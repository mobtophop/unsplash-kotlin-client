package com.example.unsplashclient.ui.main_fragment.quick_search_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.unsplashclient.R
import com.example.unsplashclient.databinding.QuickSearchButtonBinding

class QuickSearchAdapter() :
    ListAdapter<QuickSearchData, QuickSearchViewHolder<*>>(QuickSearchItemComparator()) {

    companion object {
        private const val QUICK_SEARCH = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is QuickSearchData -> QUICK_SEARCH
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuickSearchViewHolder<*> {

        return when (viewType) {
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

    override fun onBindViewHolder(holder: QuickSearchViewHolder<*>, position: Int) {
        when (val item = getItem(position)) {
            is QuickSearchData -> (holder as? QuickSearchHolder)?.bind(item)
            else -> return
        }
    }

    inner class QuickSearchHolder(
        var binding: QuickSearchButtonBinding
    ) : QuickSearchViewHolder<QuickSearchData>(binding.root) {
        override fun bind(data: QuickSearchData) {
            binding.label = data.label
            binding.icon.setImageDrawable(
                AppCompatResources.getDrawable(
                    binding.root.context,
                    data.recourse
                )
            )
            binding.executePendingBindings()

            binding.root.setOnClickListener { data.searchCallback.invoke() }
        }
    }
}