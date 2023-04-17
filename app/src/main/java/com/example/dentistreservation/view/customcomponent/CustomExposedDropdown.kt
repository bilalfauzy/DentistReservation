package com.example.dentistreservation.view.customcomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize


@Composable
fun CustomExposedDropdown(
    options: List<String>,
    label: String,
    onOptionSelected: (String) -> Unit,
    selectedOption: String? = null
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(options.indexOf(selectedOption)) }
    var textFielSize by remember {
        mutableStateOf(Size.Zero)
    }

    Column{
        OutlinedTextField(
            value = selectedOption ?: "",
            onValueChange = {},
            label = {
                Text(text = label)
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        expanded = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null
                    )
                }
            },
            singleLine = true,
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    textFielSize = it.size.toSize()
                }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) {
                textFielSize.width.toDp()
            })
                .background(Color.White)
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    onClick = {
                        selectedIndex = index
                        onOptionSelected(option)
                        expanded = false
                    },
                ) {
                    Text(
                        text = option,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }

//    Box(modifier = Modifier.wrapContentSize()) {
//        Box(
//            modifier = Modifier
//                .clickable { expanded = true }
//                .background(Color.White)
//                .padding(horizontal = 16.dp, vertical = 8.dp)
//                .height(48.dp)
//                .fillMaxWidth(),
//            contentAlignment = Alignment.CenterStart
//        ) {
//            Text(
//                text = if (selectedOption != null) options[selectedIndex] else label,
//                style = MaterialTheme.typography.body1
//            )
//
//            Icon(
//                imageVector = Icons.Filled.ArrowDropDown,
//                contentDescription = null,
//                modifier = Modifier.align(Alignment.CenterEnd)
//            )
//        }
//
//    }
}

