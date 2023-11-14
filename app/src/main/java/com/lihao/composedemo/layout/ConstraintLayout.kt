package com.lihao.composedemo.layout

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        val (button, text) = createRefs()
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text(text = "Button")
        }
        Text(
            text = "Text",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(button.bottom, margin = 16.dp)
                centerHorizontallyTo(parent)
            }
        )
    }
}

@Composable
fun ConstraintLayoutContent2() {
    ConstraintLayout {
        val (button1, button2, text) = createRefs()
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text(text = "Button 1")
        }
        Text(
            text = "Text",
            Modifier.constrainAs(text) {
                top.linkTo(button1.bottom, margin = 16.dp)
                centerAround(button1.end)
            }
        )
        val barrier = createEndBarrier(button1, text)
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text(text = "Button 1")
        }
    }
}

@Composable
fun LargeLayoutContent() {
    ConstraintLayout {
        val (text) = createRefs()
        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text(
            text = "This is very very very very very very very very very very very long text.",
            Modifier.constrainAs(text) {
                linkTo(
                    start = guideline,
                    end = parent.end
                )
                width = Dimension.preferredWrapContent
            }
        )
    }
}