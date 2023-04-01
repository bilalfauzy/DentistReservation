package com.example.dentistreservation.view

import androidx.compose.foundation.background
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.dentistreservation.ui.theme.baseColor

@Composable
fun MyButton(
    onClick: () -> Unit,
    textColor: Color,
    text: String
) {
    Button(
        onClick = onClick
    ) {
        Text(
            text = text,
            color = textColor
        )
    }
}