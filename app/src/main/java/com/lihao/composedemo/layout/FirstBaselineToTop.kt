package com.lihao.composedemo.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lihao.composedemo.ui.theme.ComposeDemoTheme

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = layout { measurable, constraints ->
    // 测量元素。
    val placeable = measurable.measure(constraints)
    // 元素已有基线的位置。
    val firstBaseline = placeable[FirstBaseline]
    /* 元素新的Y坐标：给定的参数（基线距离顶部距离） - 基线位置。
    这样减去后剩下的就是元素顶部距离上边的距离，可以当Y坐标用。*/
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    // 注意这里的高是控件下边沿至控件之外上边的总高。
    val height = placeable.height + placeableY
    // 重新布局。宽不变，高变了。
    layout(placeable.width, height) {
        // 把上边距换成新的placeableY。
        placeable.placeRelative(0, placeableY)
    }
}

@Composable
fun TextWithPaddingToBaseline() {
    Row {
        Text(
            text = "普通的边距",
            modifier = Modifier
                .padding(24.dp)
                .background(Color.Green)
        )
        Text(
            text = "基线距离上边距",
            modifier = Modifier
                .firstBaselineToTop(24.dp)
                .background(Color.Red)
        )
    }
}