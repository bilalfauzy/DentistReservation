package com.example.dentistreservation.view.reservasi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dentistreservation.view.MyAppBar
import com.example.dentistreservation.viewmodel.reservasi.MelakukanPembayaranVM
import kotlin.math.tan

@Composable
fun MelakukanPembayaran(
    navController: NavHostController,
    melakukanPembayaranVM: MelakukanPembayaranVM,
    namaDok: String,
    tanggal: String,
    hari: String,
    jam: String,
    keluhan: String
){
    Column {
        val biaya = remember{
            mutableStateOf("Rp50.000")
        }
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
            Text(text = "Nama dokter : ${namaDok}\n")
            Text(text = "Tanggal : ${hari}/${tanggal}\n")
            Text(text = "Jam : ${jam}\n")
            Text(text = "Keluhan/penyakit : ${keluhan}\n")

            Row() {
                Text(text = "Shopeepay")
                Text(text = "Gopay")
            }

            Text(text = "Biaya total : ${biaya}\n")
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                }
            ) {
                Text(text = "Bayar")
            }
        }
    }
}