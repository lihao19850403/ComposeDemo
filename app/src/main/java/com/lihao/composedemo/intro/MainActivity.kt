package com.lihao.composedemo.intro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lihao.composedemo.R
import com.lihao.composedemo.layout.SampleData
import com.lihao.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                MessageList(messages = SampleData.conversationSample)
            }
        }
    }

    @Composable
    fun MessageCard(msg: Message) {
        Row(
            modifier = Modifier
                .padding(
                    all = 5.dp
                )
                .background(color = MaterialTheme.colors.background)
        ) {
            var isExpanded by remember {
                mutableStateOf(false)
            }
            val surfaceColor: Color by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
            )
            Image(
                painter = painterResource(
                    id = R.drawable.demo_cat
                ),
                contentDescription = "这是一张图片",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = "Hello ${msg.name}!",
                    color = MaterialTheme.colors.secondaryVariant
                )
                Spacer(
                    modifier = Modifier
                        .width(40.dp)
                        .height(2.dp)
                        .background(color = Color.Red)
                )
                Surface(
                    color = surfaceColor,
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(all = 1.dp)
                ) {
                    Text(
                        text = "info：${msg.description}",
                        modifier = Modifier.padding(all = 4.dp),
                        style = MaterialTheme.typography.body2,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1
                    )
                }
            }
        }
    }

    @Composable
    fun MessageList(messages: List<Message>) {
        LazyColumn {
            items(messages) { message ->
                MessageCard(msg = message)

            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewMessageCard() {
        MessageList(messages = SampleData.conversationSample)
    }
}
