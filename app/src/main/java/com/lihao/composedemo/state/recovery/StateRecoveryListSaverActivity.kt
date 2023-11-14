package com.lihao.composedemo.state.recovery

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lihao.composedemo.ui.theme.ComposeDemoTheme
import kotlinx.parcelize.Parcelize

class StateRecoveryListSaverActivity : ComponentActivity() {

    @Parcelize // 使用Parcelize插件自动生成相关代码。
    data class City(val name: String, val country: String): Parcelable

    // 普通的数据类，不需要Parcelable化。
    data class LocalCity(val name: String, val country: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                Column {
                    CityScreenParcelable()
                    Spacer(modifier = Modifier.height(10.dp))
                    CityScreenMapSaver()
                    Spacer(modifier = Modifier.height(10.dp))
                    CityScreenListSaver()
                }
            }
        }
    }

    @Composable
    fun CityScreenParcelable() {
        val (city, setCity) = rememberSaveable {
            mutableStateOf(City("马德里", "西班牙"))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            TextButton(
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    setCity(City("北京", "中国"))
                }
            ) {
                Text(text = "使用Parcelable记录状态")
            }
            Text(
                text = "${city.country} - ${city.name}",
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }
    }

    @Composable
    fun CityScreenMapSaver() {
        val citySaver = run {
            val nameKey = "name"
            val countryKey = "country"
            mapSaver(
                save = {
                    mapOf(nameKey to it.name, countryKey to it.country)
                },
                restore = {
                    LocalCity(it[nameKey] as String, it[countryKey] as String)
                }
            )
        }

        val (city, setCity) = rememberSaveable(stateSaver = citySaver) {
            mutableStateOf(LocalCity("马德里", "西班牙"))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            TextButton(
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    setCity(LocalCity("北京", "中国"))
                }
            ) {
                Text(text = "使用MapSaver记录状态")
            }
            Text(
                text = "${city.country} - ${city.name}",
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }
    }

    @Composable
    fun CityScreenListSaver() {
        val citySaver = listSaver<LocalCity, Any>(
            save = {
                listOf(it.name, it.country)
            },
            restore = {
                LocalCity(it[0] as String, it[1] as String) // 实际上还是用MapSaver合适，这里为了演示，直接用列表前两项元素分别存储键值对。
            }
        )

        val (city, setCity) = rememberSaveable(stateSaver = citySaver) {
            mutableStateOf(LocalCity("马德里", "西班牙"))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            TextButton(
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    setCity(LocalCity("北京", "中国"))
                }
            ) {
                Text(text = "使用ListSaver记录状态")
            }
            Text(
                text = "${city.country} - ${city.name}",
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }
    }
}
