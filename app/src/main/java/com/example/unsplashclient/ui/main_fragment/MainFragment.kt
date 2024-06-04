package com.example.unsplashclient.ui.main_fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.unsplashclient.base.BaseFragment
import com.example.unsplashclient.R
import com.example.unsplashclient.databinding.FragmentMainBinding
import com.example.unsplashclient.ui.main_fragment.main_list_adapter.MainListAdapter
import dagger.hilt.android.AndroidEntryPoint

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

//        viewLifecycleOwner.lifecycleScope.launch {
//            mainListAdapter.submitData(
//                PagingData.from(
//                    viewModel.mainListBaseSource.value
//                )
//            )
//        }

        viewModel.photos.observe(viewLifecycleOwner) {
            mainListAdapter.submitData(viewLifecycleOwner.lifecycle, it.map { data -> data })
        }
    }

    override fun layoutBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.view = this
        binding.viewModel = viewModel
        binding.mainListAdapter = mainListAdapter
    }
}