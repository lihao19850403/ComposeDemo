package com.lihao.composedemo.guesture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lihao.composedemo.ui.theme.ComposeDemoTheme

class MainActivityGesture : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
//                GestureSample()
                ScrollBoxes()
            }
        }
    }
}

