package com.example.dentistreservation.view.reservasi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dentistreservation.view.MyAppBar

@Preview
@Composable
fun MemilihTanggal(){
    val selectedDate = remember { mutableStateOf("") }
    
    Column() {
        MyAppBar(
            title = "Memilih tanggal",
            navigationIcon = Icons.Filled.ArrowBack,
            onNavigationClick = {

            }
        )

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(40.dp)
        ) {
            Text(text = "Nama dokter gigi")
            Text(text = "Pilih tanggal")

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = selectedDate.value,
                    onValueChange = { selectedDate.value = it },
                    label = { Text("Pilih Tanggal") }
                )
                IconButton(
                    onClick = { /* Show date picker */ },
                    modifier = Modifier.offset(10.dp)
                ) {
                    Icon(Icons.Filled.DateRange, contentDescription = null)
                }
            }

            OutlinedTextField(
                value = selectedDate.value,
                onValueChange = {
                    selectedDate.value = it
                },
                label = {
                    Text("Isi keluhan")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Pilih")
            }
        }
    }
}