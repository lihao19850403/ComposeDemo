package com.lihao.composedemo.state

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.lihao.composedemo.state.todo.TodoItem
import com.lihao.composedemo.state.todo.TodoItemInput
import com.lihao.composedemo.state.todo.TodoScreen
import com.lihao.composedemo.state.todo.TodoViewModel
import com.lihao.composedemo.ui.theme.ComposeDemoTheme

class MainActivityState : ComponentActivity() {

    private val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                TodoActivityScreen()
//                TodoItemInput(onItemComplete = {
//                    Log.e("stats", it.task)
//                })
            }
        }
    }

    @Composable
    fun TodoActivityScreen() {
        TodoScreen(
            items = todoViewModel.todoItems,
            onAddItem = todoViewModel::addItem,
            onRemoveItem = todoViewModel::removeItem,
            currentlyEditing = todoViewModel.currentEditItem,
            onStartEdit = todoViewModel::onEditItemSelected,
            onEditItemChange = todoViewModel::onEditItemChange,
            onEditDone = todoViewModel::onEditDone
        )
    }
}

