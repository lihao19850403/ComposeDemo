package com.lihao.composedemo.locals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lihao.composedemo.ui.theme.ComposeDemoTheme

class MainActivityLocal : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                CompositionSample()
            }
        }
    }

}

