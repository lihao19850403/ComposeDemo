package com.lihao.composedemo.guesture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lihao.composedemo.ui.theme.ComposeDemoTheme

class MainActivityGesture : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                Column {
                    GestureSample()
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ScrollBoxes()
                        Spacer(modifier = Modifier.width(10.dp))
                        ScrollBoxesSmooth()
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    ScrollableSample()
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        NestedScrollSample()
                        Spacer(modifier = Modifier.width(10.dp))
                        DragableDemo()
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }
    }
}

