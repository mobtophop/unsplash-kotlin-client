package com.example.unsplashclient.ui.main_fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2.Orientation
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.unsplashclient.base.BaseFragment
import com.example.unsplashclient.R
import com.example.unsplashclient.databinding.FragmentMainBinding
import com.example.unsplashclient.ui.main_fragment.image_preview_adapter.ImagePreviewAdapter
import com.example.unsplashclient.ui.main_fragment.quick_search_adapter.QuickSearchAdapter
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val viewModel: MainViewModel by viewModels()
    private val adapterImagePreview by lazy { ImagePreviewAdapter() }
    private val adapterQuickSearch by lazy { QuickSearchAdapter() }
    override val layoutRes = R.layout.fragment_main
    override val binding: FragmentMainBinding by viewBinding(createMethod = CreateMethod.BIND)

    override fun initUI(savedInstanceState: Bundle?) {
        initViewModel()

        binding.imagePreviewRecycler.apply {
            layoutManager = GridLayoutManager(context, 2)
        }

        val quickSearchLayoutManager = GridLayoutManager(context, 2)
        quickSearchLayoutManager.orientation = RecyclerView.HORIZONTAL

        binding.quickSearchRecycler.apply {
            layoutManager = quickSearchLayoutManager
        }
    }

    private fun initViewModel() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            listBaseSourceImagePreview.collect { dataSource ->

                adapterImagePreview.submitList(dataSource.toMutableList())
                adapterImagePreview.notifyDataSetChanged()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            listBaseSourceQuickSearch.collect { dataSource ->

                adapterQuickSearch.submitList(dataSource.toMutableList())
                adapterQuickSearch.notifyDataSetChanged()
            }
        }
    }

    override fun layoutBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.view = this
        binding.viewModel = viewModel
        binding.adapterImagePreview = adapterImagePreview
        binding.adapterQuickSearch = adapterQuickSearch
    }
}