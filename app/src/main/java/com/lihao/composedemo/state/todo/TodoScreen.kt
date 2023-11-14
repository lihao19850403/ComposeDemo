package com.lihao.composedemo.state.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lihao.composedemo.state.util.generateRandomTodoItem
import kotlin.random.Random

@Composable
fun TodoScreen(
    items: List<TodoItem>,
    currentlyEditing: TodoItem?,
    onAddItem: (TodoItem) -> Unit,
    onRemoveItem: (TodoItem) -> Unit,
    onStartEdit: (TodoItem) -> Unit,
    onEditItemChange: (TodoItem) -> Unit,
    onEditDone: () -> Unit
) {
    Column {
        val enableTopSection = (currentlyEditing == null)
        TodoItemInputBackground(elevate = true) {
            if (enableTopSection) {
                TodoItemEntryInput(onItemComplete = onAddItem)
            } else {
                Text(
                    text = "Editing Item",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(items) { todo ->
                if (currentlyEditing?.id == todo.id) {
                    TodoItemInlineEditor(
                        item = todo,
                        onEditItemChange = onEditItemChange,
                        onEditDone = onEditDone,
                        onRemoveItem = {
                            onRemoveItem(todo)
                            onEditDone()
                        }
                    )
                } else {
                    TodoRow(
                        todoItem = todo,
                        onItemClicked = { onStartEdit(todo) },
                        modifier = Modifier.fillParentMaxWidth()
                    )
                }
            }
        }
        Button(
            onClick = {
                onAddItem(generateRandomTodoItem())
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Add random item")
        }
    }
}

@Composable
fun TodoRow(
    todoItem: TodoItem,
    onItemClicked: (TodoItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                onItemClicked(todoItem)
            },
        horizontalArrangement = Arrangement.SpaceBetween // 横向均匀分布子元素。

    ) {
        val iconAlpha: Float = remember(todoItem.id) {
            Random.nextFloat().coerceIn(0.3f, 0.9f)
        }
        Text(text = todoItem.task)
        Icon(
            imageVector = todoItem.icon.imageVector,
            tint = LocalContentColor.current.copy(alpha = iconAlpha),
            contentDescription = stringResource(id = todoItem.icon.contentDescription)
        )
    }
}