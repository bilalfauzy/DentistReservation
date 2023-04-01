package com.example.dentistreservation.view.reservasi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dentistreservation.view.MyAppBar

@Composable
fun BerhasilMembayar(){
    Column() {
        MyAppBar(
            title = "Berhasil",
            navigationIcon = Icons.Filled.ArrowBack,
            onNavigationClick = {

            }
        )
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp)
        ){
            Text(text = "Nama dokter :\n")
            Text(text = "Detail Reservasi :\n")
            Text(text = "Tanggal :\n")
            Text(text = "Keluhan/penyakit :\n")
            Text(text = "Metode pembayaran :\n")

            Text(text = "Berhasil melakukan pembayaran")
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "OK")
            }
        }
    }

}