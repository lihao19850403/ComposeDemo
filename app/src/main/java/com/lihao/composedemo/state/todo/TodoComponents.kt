package com.lihao.composedemo.state.todo

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TodoItemInlineEditor(
    item: TodoItem,
    onEditItemChange: (TodoItem) -> Unit,
    onEditDone: () -> Unit,
    onRemoveItem: () -> Unit
) {
    TodoItemInput(
        text = item.task,
        setText = { onEditItemChange(item.copy(task = it)) },
        icon = item.icon,
        setIcon = { onEditItemChange(item.copy(icon = it)) },
        iconsVisible = true,
        submit = onEditDone,
        buttonSlot = {
            Row {
                val shrinkButtons = Modifier.widthIn(20.dp)
                TextButton(
                    onClick = onEditDone,
                    modifier = shrinkButtons
                ) {
                    Text(
                        text = "保存",
                        textAlign = TextAlign.End,
                    )
                }
                TextButton(
                    onClick = onRemoveItem,
                    modifier = shrinkButtons
                ) {
                    Text(
                        text = "删除️",
                        textAlign = TextAlign.End,
                    )
                }
            }
        })
}

@Composable
fun TodoItemInputBackground(
    elevate: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    val animatedElevation by animateDpAsState(
        if (elevate) 1.dp else 0.dp,
        TweenSpec(300)
    )
    Surface(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f),
        elevation = animatedElevation,
        shape = RectangleShape
    ) {
        Row(
            modifier = modifier.animateContentSize(animationSpec = TweenSpec(300)),
            content = content
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TodoInputText(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        singleLine = true,
        modifier = modifier,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        })
    )
}

@Composable
fun TodoEditButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TextButton(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(),
        modifier = modifier,
        enabled = enabled
    ) {
        Text(text = text)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedIconRow(
    icon: TodoIcon,
    onIconChange: (TodoIcon) -> Unit,
    modifier: Modifier = Modifier,
    visible: Boolean = true
) {
    val enter = remember {
        fadeIn(
            animationSpec = TweenSpec(
                300, easing = FastOutLinearInEasing
            )
        )
    }
    val exit = remember {
        fadeOut(
            animationSpec = TweenSpec(
                100, easing = FastOutSlowInEasing
            )
        )
    }
    Box(modifier.defaultMinSize(minHeight = 16.dp)) {
        AnimatedVisibility(
            visible = visible,
            enter = enter,
            exit = exit
        ) {
            IconRow(
                icon = icon,
                onIconChange = onIconChange
            )
        }
    }
}

@Composable
fun IconRow(
    icon: TodoIcon,
    onIconChange: (TodoIcon) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        for (todoIcon in TodoIcon.values()) {
            SelectableIconButton(
                icon = todoIcon.imageVector,
                iconContentDescription = todoIcon.contentDescription,
                onIconSelected = { onIconChange(todoIcon) },
                isSelected = todoIcon == icon
            )
        }
    }
}

@Composable
fun SelectableIconButton(
    icon: ImageVector,
    iconContentDescription: Int,
    onIconSelected: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val tint = if (isSelected) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
    }
    TextButton(
        onClick = { onIconSelected() },
        shape = CircleShape,
        modifier = modifier
    ) {
        Column {
            Icon(
                imageVector = icon,
                tint = tint,
                contentDescription = stringResource(id = iconContentDescription)
            )
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .padding(top = 3.dp)
                        .width(icon.defaultWidth)
                        .height(1.dp)
                        .background(tint)
                )
            } else {
                Spacer(modifier = Modifier.padding(top = 3.dp))
            }
        }
    }
}

@Composable
fun TodoItemEntryInput(onItemComplete: (TodoItem) -> Unit) {
    val (text, setText) = remember { mutableStateOf("") }
    val (icon, setIcon) = remember { mutableStateOf(TodoIcon.Default) }
    val iconsVisible = text.isNotBlank()
    val submit = {
        onItemComplete(TodoItem(text, icon))
        setText("")
        setIcon(TodoIcon.Default)
    }
    TodoItemInput(
        text = text,
        setText = setText,
        icon = icon,
        setIcon = setIcon,
        iconsVisible = iconsVisible,
        submit = submit
    ) {
        TodoEditButton(
            onClick = submit,
            text = "Add",
            enabled = text.isNotBlank()
        )
    }
}

@Composable
fun TodoItemInput(
    text: String,
    setText: (String) -> Unit,
    icon: TodoIcon,
    setIcon: (TodoIcon) -> Unit,
    iconsVisible: Boolean,
    submit: () -> Unit,
    buttonSlot: @Composable () -> Unit
) {
    Column {
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            TodoInputText(
                text = text,
                onTextChange = setText,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                onImeAction = submit
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                buttonSlot()
            }
        }

        if (iconsVisible) {
            AnimatedIconRow(
                icon = icon,
                onIconChange = setIcon,
                modifier = Modifier.padding(top = 8.dp)
            )
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}