package com.lihao.composedemo.locals

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun CompositionSample() {
    Column {
        CompositionLocalProvider(LocalElevations provides CardElevation.high) {
            MyCard(backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)) {

            }
        }
        MyCard(backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)) {

        }
    }
}