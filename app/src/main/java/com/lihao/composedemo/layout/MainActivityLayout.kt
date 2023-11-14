package com.lihao.composedemo.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lihao.composedemo.ui.theme.ComposeDemoTheme

class MainActivityLayout : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    PhotographerCard()
                    Row {
                        SimpleColumn()
                        Spacer(modifier = Modifier.width(15.dp))
                        LazyList()
                        Spacer(modifier = Modifier.width(15.dp))
                        ScrollableList()
                    }
                    TextWithPaddingToBaseline()
                    MyOwnColumnSample()
                    StaggeredGridBodyContent()
                    ConstraintLayoutContent()
                    ConstraintLayoutContent2()
                    LargeLayoutContent()
                    DecoupledConstraintLayout()
                    TwoTexts()
                }
            }
        }
    }
}

