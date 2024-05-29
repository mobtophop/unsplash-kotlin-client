package com.example.unsplashclient.ui.main_fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.unsplashclient.base.BaseFragment
import com.example.unsplashclient.R
import com.example.unsplashclient.databinding.FragmentMainBinding
import com.example.unsplashclient.ui.main_fragment.main_list_adapter.MainListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val viewModel: MainViewModel by viewModels()
    private val mainListAdapter by lazy { MainListAdapter() }
    override val layoutRes = R.layout.fragment_main
    override val binding: FragmentMainBinding by viewBinding(createMethod = CreateMethod.BIND)

    override fun initUI(savedInstanceState: Bundle?) {
        initViewModel()

        val layoutManager = GridLayoutManager(context, 2)
        binding.mainRecyclerList.apply {
            this.layoutManager = layoutManager
        }
    }

    private fun initViewModel() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            mainListBaseSource.collect { dataSource ->

                mainListAdapter.submitList(dataSource.toMutableList())
                mainListAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun layoutBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.view = this
        binding.viewModel = viewModel
        binding.mainListAdapter = mainListAdapter
    }
}