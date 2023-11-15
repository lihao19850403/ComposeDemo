package com.lihao.composedemo.guesture

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DragableDemo() {
    Box(
        modifier = Modifier
            .size(120.dp)
            .background(color = Color.Green)
    ) {
        var offsetX by remember { mutableStateOf(0f) }
        Text(
            text = "Drag me!",
            modifier = Modifier
                .size(50.dp)
                .background(color = Color.Yellow)
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        offsetX += delta
                    }
                )
        )
    }
}
