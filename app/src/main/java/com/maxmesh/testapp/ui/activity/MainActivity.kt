package com.maxmesh.testapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maxmesh.testapp.R
import com.maxmesh.testapp.ui.fragment.VideoPlayerFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, VideoPlayerFragment())
            .commit()
    }
}