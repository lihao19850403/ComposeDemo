package com.lihao.composedemo.guesture

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun DragableDemo() {
    var offsetX by remember { mutableStateOf(0f) }
    Text(
        text = "Drag me!",
        modifier = Modifier.draggable(
            orientation = Orientation.Horizontal,
            state = rememberDraggableState { delta ->
                offsetX += delta
            }
        )
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableDemo() {
    val width = 96.dp
    val squareSize = 48.dp
    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) {
        squareSize.toPx()
    }
    val anchors = mapOf(0f to 0, sizePx to 1)
    Box(
        modifier = Modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.LightGray)
    ) {
        Box(modifier = Modifier
            .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
            .size(squareSize)
            .background(Color.DarkGray)
        )
    }
}