package com.lihao.composedemo.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SimpleColumn() {
    val scrollState = rememberScrollState()
    // Column默认不能垂直滚动，需要增加相关修饰器。
    Column(
        Modifier.height(100.dp)
            .verticalScroll(scrollState)
    ) {
        repeat(100) {
            Text(
                text = "Item: #$it",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
fun LazyList() {
    LazyColumn(
        modifier = Modifier.height(100.dp)
    ) {
        items(100) {
            Text(
                text = "Item: #$it",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
fun ScrollableList() {
    val listSize = 100
    val lastIndex = listSize - 1
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            Button(
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Scroll to the top")
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(lastIndex)
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Scroll to the end")
            }
        }
        LazyColumn(
            state = scrollState,
            modifier = Modifier.height(100.dp)
        ) {
            items(100) {
                Text(
                    text = "Item: #$it",
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}
