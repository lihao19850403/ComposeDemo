package com.lihao.composedemo.layout

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measureables, constraints ->
        var totalHeight = 0
        val placeables = measureables.map {
            val placeable = it.measure(constraints)
            totalHeight += placeable.height + 20 // 此处提前确定了控件总高度。
            placeable
        }
        var yPosition = 0
        layout(constraints.maxWidth, totalHeight) {
            placeables.forEach {
                it.placeRelative(x = 0, y = yPosition)
                yPosition += it.height + 20
            }
        }
    }
}

@Composable
fun MyOwnColumnSample() {
    MyOwnColumn {
        Text(text = "MyOwnColumn")
        Text(text = "places times")
        Text(text = "vertically.")
        Text(text = "we've done it by hand!")
    }
}