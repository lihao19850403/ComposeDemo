package com.lihao.composedemo.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

val topics = listOf(
    "Hi there!",
    "测试信息",
    "床前明月光，疑是地上霜",
    "新闻",
    "长度不等的字符串",
    "winter is coming",
    "Android Kotlin Jetpack Compose",
    "英文信息",
    "超级玛丽",
    "1234567890",
    "！@#￥%……&*（））",
)

@Composable
fun StaggeredGrid(
    modifier: Modifier = Modifier,
    rows: Int = 3, // 默认行数。
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val rowWidth = IntArray(rows) { 0 } // 行宽。
        val rowHeight = IntArray(rows) { 0 } // 行高。
        val placeables = measurables.mapIndexed { index, measurable ->
            val placeable = measurable.measure(constraints)
            val row = index % rows // 元素所在行号。
            rowWidth[row] += placeable.width // 计算出行宽。
            rowHeight[row] = max(rowHeight[row], placeable.height) // 计算出行高。
            placeable
        }
        val width = rowWidth.maxOrNull() ?: constraints.minWidth // 控件最大宽度。
        val height = rowHeight.sumOf { it } // 控件累加高度和。
        val rowY = IntArray(rows) { 0 } // 元素Y坐标值。
        for (i in 1 until rows) {
            rowY[i] = rowY[i - 1] + rowHeight[i - 1]
        }
        layout(width, height) {
            val rowX = IntArray(rows) { 0 } // 元素X坐标值。
            placeables.forEachIndexed { index, placeable ->
                val row = index % rows
                placeable.placeRelative(
                    x = rowX[row],
                    y = rowY[row]
                )
                rowX[row] += placeable.width
            }
        }
    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String
) {
    Card(
        modifier = modifier,
        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp, 16.dp)
                    .background(color = MaterialTheme.colors.secondary)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text)
        }
    }
}

@Composable
fun StaggeredGridBodyContent(
    modifier: Modifier = Modifier
) {
    Row( // 增加这个是为了整个控件能左右滑动。
        modifier = modifier
            .background(color = Color.LightGray)
            .padding(16.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        StaggeredGrid(modifier = Modifier) {
            for (topic in topics) {
                Chip(text = topic, modifier = Modifier.padding(8.dp))
            }
        }
    }
}