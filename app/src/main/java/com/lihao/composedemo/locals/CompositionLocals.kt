package com.lihao.composedemo.locals

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun CompositionSample() {
    Column {
        /*
        这里的LocalElevations中记录的海拔信息是0dp，当提供了CardElevation.high后，里面的卡片海拔将变为10dp。
        当CompositionLocalProvider作用域结束后，LocalElevations恢复了默认的0dp海拔高度。
         */
        CompositionLocalProvider(LocalElevations provides CardElevation.high) {
            MyCard(backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)) {

            }
        }
        // 此处已经脱离了CompositionLocalProvider的作用域范围，这个卡片的海拔值将会使用默认的0dp。
        MyCard(backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)) {

        }
    }
}