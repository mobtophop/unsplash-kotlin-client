package com.example.unsplashclient.ui.main_fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.unsplashclient.MainActivity
import com.example.unsplashclient.base.BaseFragment
import com.example.unsplashclient.R
import com.example.unsplashclient.databinding.FragmentMainBinding
import com.example.unsplashclient.ui.main_fragment.image_preview_adapter.ImagePreviewAdapter
import com.example.unsplashclient.ui.main_fragment.quick_search_adapter.QuickSearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val viewModel: MainViewModel by viewModels<MainViewModel>(
        extrasProducer = {
            defaultViewModelCreationExtras.withCreationCallback<MainViewModel.MainViewModelFactory> { factory ->
                factory.create(state = SavedStateHandle())
            }
        },
    )
    private val imagePreviewAdapter by lazy {
        ImagePreviewAdapter(
            callback = { imageUrl, color, authorName ->

                (activity as MainActivity).openImageViewFragment(
                    authorName = authorName,
                    imageUrl = imageUrl,
                    color = color,
                    postUrl = null,
                )

            }
        )
    }
    private val quickSearchAdapter by lazy { QuickSearchAdapter() }
    override val layoutRes = R.layout.fragment_main
    override val binding: FragmentMainBinding by viewBinding(createMethod = CreateMethod.BIND)

    fun passNewSearchQueryToViewModel(str: String?) {
        viewModel.setNewSearchQuery(str)
    }

    override fun initUI(savedInstanceState: Bundle?) {
        initViewModel()

        val layoutManager = GridLayoutManager(context, 2)
        binding.mainRecyclerList.apply {
            this.layoutManager = layoutManager
        }
    }

    private fun initViewModel() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            quickSearchBaseSource.collect { dataSource ->
                quickSearchAdapter.submitList(dataSource.toMutableList())
                quickSearchAdapter.notifyDataSetChanged()
            }
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            imagePreviewAdapter.submitData(viewLifecycleOwner.lifecycle, it.map { data -> data })
        }
    }

    override fun layoutBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.view = this
        binding.viewModel = viewModel
        binding.mainListAdapter = ConcatAdapter(quickSearchAdapter, imagePreviewAdapter)
    }
}