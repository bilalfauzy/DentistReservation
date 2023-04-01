package com.example.dentistreservation.view.reservasi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun MelakukanPembayaran(){
    Column() {
        MyAppBar(
            title = "Pembayaran",
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
            Text(text = "Pilih metode pembayaran")
            Text(text = "Detail Reservasi :\n")
            Text(text = "Tanggal :\n")
            Text(text = "Keluhan/penyakit :\n")
            Row() {
                Text(text = "Shopeepay")
                Text(text = "Gopay")
            }

            Text(text = "Biaya total :\n")
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Bayar")
            }
        }
    }
}