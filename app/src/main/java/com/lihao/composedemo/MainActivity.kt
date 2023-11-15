package com.lihao.composedemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lihao.composedemo.guesture.MainActivityFreeDrag
import com.lihao.composedemo.guesture.MainActivityGesture
import com.lihao.composedemo.intro.MainActivity
import com.lihao.composedemo.layout.MainActivityLayout
import com.lihao.composedemo.layout.ScaffoldActivity
import com.lihao.composedemo.locals.MainActivityLocal
import com.lihao.composedemo.state.MainActivityState
import com.lihao.composedemo.state.recovery.StateRecoveryListSaverActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageBox(msg = "world")
        }
    }

    @Composable
    fun MessageBox(msg: String) {
        Column {
            // Row横向排列控件。
            Row(modifier = Modifier.padding(all = 10.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.demo_cat),
                    contentDescription = "替代说明",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                // Column纵向排列控件。
                Column(modifier = Modifier.background(Color.Cyan)) {
                    Text(text = "Hello $msg")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Second")
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            // 暂存上下文环境，用于跳转Activity。
            val mContext = LocalContext.current
            Text(
                text = "1，跳转至列表页面",
                modifier = Modifier
                    .background(color = Color.Green)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable {
                        mContext.startActivity(Intent(mContext, MainActivity::class.java))
                    }
                    .padding(all = 10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "2，跳转至脚手架页面（脚手架页面默认占满全屏）",
                modifier = Modifier
                    .background(color = Color.Green)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable {
                        mContext.startActivity(Intent(mContext, ScaffoldActivity::class.java))
                    }
                    .padding(all = 10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "3，跳转至布局页面",
                modifier = Modifier
                    .background(color = Color.Green)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable {
                        mContext.startActivity(Intent(mContext, MainActivityLayout::class.java))
                    }
                    .padding(all = 10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "4，跳转至待办事项页面",
                modifier = Modifier
                    .background(color = Color.Green)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable {
                        mContext.startActivity(Intent(mContext, MainActivityState::class.java))
                    }
                    .padding(all = 10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "5，跳转至状态保存页面",
                modifier = Modifier
                    .background(color = Color.Green)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable {
                        mContext.startActivity(Intent(mContext, StateRecoveryListSaverActivity::class.java))
                    }
                    .padding(all = 10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "6，跳转至传参页面",
                modifier = Modifier
                    .background(color = Color.Green)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable {
                        mContext.startActivity(Intent(mContext, MainActivityLocal::class.java))
                    }
                    .padding(all = 10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "7，跳转至手势页面",
                modifier = Modifier
                    .background(color = Color.Green)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable {
                        mContext.startActivity(Intent(mContext, MainActivityGesture::class.java))
                    }
                    .padding(all = 10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "8，跳转至自由拖动页面",
                modifier = Modifier
                    .background(color = Color.Green)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable {
                        mContext.startActivity(Intent(mContext, MainActivityFreeDrag::class.java))
                    }
                    .padding(all = 10.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    @Preview
    @Composable
    fun previewMessageBox() {
        MessageBox(msg = "预览")
    }
}
