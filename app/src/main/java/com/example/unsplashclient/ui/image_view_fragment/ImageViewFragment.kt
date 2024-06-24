package com.example.unsplashclient.ui.image_view_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.unsplashclient.MainActivity
import com.example.unsplashclient.R
import com.example.unsplashclient.api.UnsplashPhotoData
import com.example.unsplashclient.base.BaseFragment
import com.example.unsplashclient.databinding.FragmentImageViewBinding
import com.example.unsplashclient.ui.main_fragment.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait

@AndroidEntryPoint
class ImageViewFragment : BaseFragment<FragmentImageViewBinding>() {

    private val viewModel: ImageViewViewModel by viewModels<ImageViewViewModel>(
        extrasProducer = {
            defaultViewModelCreationExtras.withCreationCallback<ImageViewViewModel.ImageViewViewModelFactory> { factory ->
                factory.create(state = SavedStateHandle())
            }
        },
    )

    override val layoutRes = R.layout.fragment_image_view
    override val binding: FragmentImageViewBinding by viewBinding(createMethod = CreateMethod.BIND)

    private val imageUrl by lazy { arguments?.getString("IMAGE_URL") }
    private val color by lazy { arguments?.getString("COLOR") }
    private val authorName by lazy { arguments?.getString("AUTHOR_NAME") }

    private val postUrl by lazy { arguments?.getString("POST_URL") }

    override fun initUI(savedInstanceState: Bundle?) {

    }

    override fun onDestroyView() {

        (activity as MainActivity).toggleSearchBar(true)
        (activity as MainActivity).setTitle("Gallery")


        super.onDestroyView()
    }

    override fun layoutBinding() {
        binding.isContentReady = postUrl == null
        binding.imageUrl = imageUrl
        binding.color = color
        binding.authorName = authorName
        binding.lifecycleOwner = viewLifecycleOwner
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_view, container, false)
    }

    override fun onStart() {
        super.onStart()

        postUrl?.let {
            requireActivity().lifecycleScope.async {
                val data = viewModel.getPostInfo(it.split("/").last())
                Thread.sleep(3000) //Added for visual representation of a long loading process
                if (data == null) return@async it

                requireActivity().runOnUiThread {
                    binding.imageUrl = data.imageUrl
                    (requireActivity() as MainActivity).setTitle(data.authorName)
                    binding.color = data.color
                    binding.isContentReady = true
                }
            }
        }?.start()
    }
}

