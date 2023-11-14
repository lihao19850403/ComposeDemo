package com.lihao.composedemo.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lihao.composedemo.ui.theme.ComposeDemoTheme

class ScaffoldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                ScaffoldStudy()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeDemoTheme {
        ScaffoldStudy()
    }
}

@Composable
fun ScaffoldStudy() {
    // 当然脚手架之前也可以添加其他控件，但从脚手架开始，会占满全屏。
    Scaffold(
        topBar = {
            TopAppBar(
                title = { androidx.compose.material.Text(text = "LayoutStudy") },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "标题图标"
                        )
                    }
                })
        }
    ) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        androidx.compose.material.Text(text = "Hi there!")
        androidx.compose.material.Text(text = "Thanks for watching me.")
    }
}