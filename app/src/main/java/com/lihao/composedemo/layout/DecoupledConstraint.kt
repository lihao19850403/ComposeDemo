package com.lihao.composedemo.layout

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraints(16.dp)
        } else {
            decoupledConstraints(160.dp)
        }
        ConstraintLayout(constraintSet = constraints) { // 从外部传入约束关系，而不是在布局内部自己写。
            Button(onClick = { }, modifier = Modifier.layoutId("button")) {
                Text(text = "Button")
            }
            Text(text = "Text", modifier = Modifier.layoutId("text"))
        }
    }
}


/**
 * 此处自定义一个约束集合。
 */
private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")
        constrain(button) {
            top.linkTo(parent.top, margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}