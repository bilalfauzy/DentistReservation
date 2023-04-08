package com.example.dentistreservation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomExposedDropdown(
    options: List<String>,
    label: String,
    onOptionSelected: (String) -> Unit,
    selectedOption: String? = null
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(options.indexOf(selectedOption)) }

    Box(modifier = Modifier.wrapContentSize()) {
        Box(
            modifier = Modifier
                .clickable { expanded = true }
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(48.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = if (selectedOption != null) options[selectedIndex] else label,
                style = MaterialTheme.typography.body1
            )

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
        
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    onClick = {
                        selectedIndex = index
                        onOptionSelected(option)
                        expanded = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = option,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}
