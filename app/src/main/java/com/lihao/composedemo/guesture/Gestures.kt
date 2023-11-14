package com.lihao.composedemo.guesture

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GestureSample() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        ClickableSample()
    }
}

@Composable
fun ClickableSample() {
    val count = remember { mutableStateOf(0) }
    Text(
        text = count.value.toString(),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .wrapContentSize()
            .background(Color.LightGray)
            .padding(
                horizontal = 50.dp,
                vertical = 40.dp
            )
//            .clickable {
//                count.value++
//            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { Log.e("stats", "按下") },
                    onDoubleTap = { }
                )
            }
    )
}