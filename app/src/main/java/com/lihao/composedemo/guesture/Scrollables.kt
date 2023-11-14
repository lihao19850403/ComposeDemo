package com.lihao.composedemo.guesture

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ScrollBoxes() {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(100.dp)
            .verticalScroll(rememberScrollState())
    ) {
        repeat(100) {
            Text(text = "Item: $it", modifier = Modifier.padding(2.dp))
        }
    }
}

@Composable
fun ScrollBoxesSmooth() {
    val state = rememberScrollState()
    LaunchedEffect(Unit) {
        state.animateScrollTo(100)
    }
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(100.dp)
            .verticalScroll(state)
    ) {
        repeat(100) {
            Text(text = "Item: $it", modifier = Modifier.padding(2.dp))
        }
    }
}

@Composable
fun ScrollableSample() {
    var offset by remember {
        mutableStateOf(0f)
    }
    Box(
        modifier = Modifier
            .size(150.dp)
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollableState { delta ->
                    offset += delta
                    delta
                }
            )
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(text = offset.toString())
    }
}

@Composable
fun NestedScrollSample() {
    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {
        Column {
            repeat(6) {
                Box(
                    modifier = Modifier
                        .height(128.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Scroll here",
                        modifier = Modifier
                            .border(12.dp, Color.DarkGray)
                            .background(Color.Yellow)
                            .padding(24.dp)
                            .height(150.dp)
                    )
                }
            }
        }
    }
}