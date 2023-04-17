package com.example.dentistreservation.view.dashboard

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
import androidx.navigation.NavHostController
import com.example.dentistreservation.view.customcomponent.MyAppBar
import com.example.dentistreservation.viewmodel.ReservasiViewModel

@Composable
fun DetailReservasi(
    navController: NavHostController,
    reservasiViewModel: ReservasiViewModel
){
    Column() {
        MyAppBar(
            title = "Profile",
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
                Text(text = "Logout")
            }
        }
    }

}