package com.maxmesh.testapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maxmesh.testapp.databinding.FragmentVideoPlayerBinding

class VideoPlayerFragment : Fragment() {

    private var _binding: FragmentVideoPlayerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VideoPlayerViewModel by viewModels()
    private val adapter = PostersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initAdapter(adapter)
        clickOnTextButton()
        clickOnBackButton()
    }

    private fun initAdapter(adapter: PostersAdapter) {
        binding.recyclerViewPoster.adapter = adapter
        viewModel.postersLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun clickOnBackButton() = with(binding) {
        backButton.setOnClickListener { editText.visibility = View.GONE }
    }

    private fun clickOnTextButton() {
        binding.textButton.setOnClickListener {
            binding.editText.visibility = View.VISIBLE
            viewModel.moveEditText(binding.editText)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

