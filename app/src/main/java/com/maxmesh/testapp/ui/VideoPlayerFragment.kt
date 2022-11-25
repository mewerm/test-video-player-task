package com.maxmesh.testapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.maxmesh.testapp.databinding.FragmentVideoPlayerBinding

private var x: Double = 0.0
private var y: Double = 0.0

class VideoPlayerFragment : Fragment() {

    private var _binding: FragmentVideoPlayerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        clickOnTextButton()
        clickOnBackButton()
    }

    private fun clickOnBackButton() = with(binding) {
        backButton.setOnClickListener { editText.visibility = View.GONE }
    }

    private fun clickOnTextButton() {
        binding.textButton.setOnClickListener {
            binding.editText.visibility = View.VISIBLE
            moveEditText()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun moveEditText() = with(binding) {
        editText.doAfterTextChanged {
            editText.setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        x = view.x.toDouble() - event.rawX
                        y = view.y.toDouble() - event.rawY
                        editText.clearFocus()
                        false
                    }
                    MotionEvent.ACTION_MOVE -> {
                        editText.animate()
                            .x(event.rawX + x.toFloat())
                            .y(event.rawY + y.toFloat())
                            .setDuration(0)
                            .start()
                        editText.clearFocus()
                        false
                    }
                    MotionEvent.ACTION_UP -> {
                        editText.clearFocus()
                        false
                    }
                    else -> {
                        editText.clearFocus()
                        false
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

