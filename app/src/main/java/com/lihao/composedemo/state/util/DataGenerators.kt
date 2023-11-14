package com.lihao.composedemo.state.util

import com.lihao.composedemo.state.todo.TodoIcon
import com.lihao.composedemo.state.todo.TodoItem

val messages = listOf(
    "Learn compose",
    "Learn state",
    "Build dynamic UIs",
    "Learn Unidirectional Data Flow",
    "Integrate LiveData",
    "Integrate ViewModel",
    "Remember to savedState!",
    "Build stateless composables",
    "Use state from stateless composables"
)

fun generateRandomTodoItem(): TodoItem {
    val message = messages.random()
    val icon = TodoIcon.values().random()
    return TodoItem(message, icon)
}