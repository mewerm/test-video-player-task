package com.maxmesh.testapp.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxmesh.testapp.data.network.apiServices
import com.maxmesh.testapp.databinding.FragmentVideoPlayerBinding
import com.maxmesh.testapp.domain.entity.MultimediaEntity
import kotlinx.coroutines.launch

class VideoPlayerViewModel : ViewModel() {

    private val _postersLiveData = MutableLiveData<List<MultimediaEntity>>()
    val postersLiveData: LiveData<List<MultimediaEntity>> = _postersLiveData

    init {
        viewModelScope.launch {
            try {
                val data = apiServices?.getDataFromAPI()
                _postersLiveData.postValue(data!!)
            } catch (e: Exception) {
                // todo catch
            }
        }
    }

    fun loadVideo(data: MultimediaEntity, binding: FragmentVideoPlayerBinding) = with(binding) {
        videoPlayer.setVideoURI(Uri.parse(data.file_url))
        videoPlayer.start()
        videoPlayer.setOnCompletionListener {
            videoPlayer.start()
        }
    }


    private var x: Double = 0.0
    private var y: Double = 0.0
    @SuppressLint("ClickableViewAccessibility")
    fun moveEditText(editText: EditText) {
        editText.doAfterTextChanged {
            editText.setOnTouchListener { view, event ->
                when (event.action) {
                    android.view.MotionEvent.ACTION_DOWN -> {
                        x = view.x.toDouble() - event.rawX
                        y = view.y.toDouble() - event.rawY
                        editText.clearFocus()
                        false
                    }
                    android.view.MotionEvent.ACTION_MOVE -> {
                        editText.animate()
                            .x(event.rawX + x.toFloat())
                            .y(event.rawY + y.toFloat())
                            .setDuration(0)
                            .start()
                        editText.clearFocus()
                        false
                    }
                    android.view.MotionEvent.ACTION_UP -> {
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
}

