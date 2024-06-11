package com.example.unsplashclient.ui.image_view_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.unsplashclient.MainActivity
import com.example.unsplashclient.R
import com.example.unsplashclient.base.BaseFragment
import com.example.unsplashclient.databinding.FragmentImageViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageViewFragment : BaseFragment<FragmentImageViewBinding>() {

    override val layoutRes = R.layout.fragment_image_view
    override val binding: FragmentImageViewBinding by viewBinding(createMethod = CreateMethod.BIND)

    private val imageUrl by lazy { arguments?.getString("IMAGE_URL") }
    private val color by lazy { arguments?.getString("COLOR") }
    private val authorName by lazy { arguments?.getString("AUTHOR_NAME") }

    override fun initUI(savedInstanceState: Bundle?) {
//        Log.d(
//            ":: LOG ARGS ::",
//            "${(activity as MainActivity?)?.navContr?.currentDestination?.arguments ?: "nothing"}"
//        )
//        (activity as MainActivity?)?.navContr?.currentDestination?.arguments?.get("IMAGE_URL")?.defaultValue.let {
//            binding.textLabel = "$it"
//            Log.d(" :: SET NEW VALUE :: ", "$it")
//        }

//        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun layoutBinding() {
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

}