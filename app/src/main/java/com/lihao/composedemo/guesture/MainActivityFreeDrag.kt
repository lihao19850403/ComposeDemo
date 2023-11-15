package com.lihao.composedemo.guesture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.lihao.composedemo.guesture.ui.theme.ComposeDemoTheme
import kotlin.math.roundToInt

class MainActivityFreeDrag : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                Column {
                    SwipeableDemo()
                    FreeDrag()
                }
                Transformable()
            }
        }
    }
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
            .background(Color.LightGray),
    ) {
        Box(modifier = Modifier
            .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
            .size(squareSize)
            .background(Color.DarkGray)
        )
    }
}

@Composable
fun FreeDrag() {
    Box(modifier = Modifier.fillMaxSize()) {
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }
        Box(modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .background(color = Color.Blue)
            .size(50.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }) {
        }
    }
}

@Composable
fun Transformable() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        var scale by remember { mutableStateOf(1f) }
        var rotation by remember { mutableStateOf(0f) }
        var offset by remember { mutableStateOf(Offset.Zero) }
        val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
            scale *= zoomChange
            rotation += rotationChange
            offset += panChange
        }
        Box(modifier = Modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                rotationZ = rotation
                translationX = offset.x
                translationY = offset.y
            }
            .transformable(state)
            .background(color = Color.Blue)
            .size(width = 100.dp, height = 200.dp)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComposeDemoTheme {
        Column {
            SwipeableDemo()
            FreeDrag()
        }
        Transformable()
    }
}