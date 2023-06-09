package com.example.dentistreservation.view.dashboard

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dentistreservation.model.Reservasi
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.view.customcomponent.CustomCard
import com.example.dentistreservation.view.customcomponent.CustomDivider
import com.example.dentistreservation.view.customcomponent.MyAppBar
import com.example.dentistreservation.viewmodel.ReservasiViewModel
import com.example.dentistreservation.viewmodel.UsersViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ListReservasi(
    navController: NavHostController,
    reservasiViewModel: ReservasiViewModel
){
    val emailLogin = FirebaseAuth.getInstance().currentUser?.email

    reservasiViewModel.getReservasi(emailLogin!!)
    reservasiViewModel.getAllReservasi()
    val reservasiList by reservasiViewModel.reservasiList.collectAsState(emptyList())
    val allReservasi by reservasiViewModel.allRes.collectAsState(emptyList())

    val context = LocalContext.current

    if (emailLogin.isNotEmpty() && emailLogin.equals("admin@gmail.com")){
        Column() {
            MyAppBar(
                title = "History reservasi",
                navigationIcon = Icons.Filled.ArrowBack,
                onNavigationClick = {
                    navController.navigate(Screen.AdminHomeScreen.route)
                }
            )

            MyListReservasi(
                allReservasi,
                onItemClick = {
                    navController.navigate(Screen.DetailReservasiScreen.route)
                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }else {
        Column() {
            MyAppBar(
                title = "History reservasi",
                navigationIcon = Icons.Filled.ArrowBack,
                onNavigationClick = {
                    navController.navigate(Screen.HomeScreen.route)
                }
            )
            MyListReservasi(
                reservasiList,
                onItemClick = {
                    navController.navigate(Screen.DetailReservasiScreen.route)
                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

}

@Composable
fun MyListReservasi(
    items: List<Reservasi>,
    onItemClick: (Reservasi) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, bottom = 20.dp)
    ) {
        items(items) { reservasi ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onItemClick(reservasi) }),
                elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ){
                    Text(text = "Nama  : ${reservasi.namaUser}")
                    Text(text = "Dokter : ${reservasi.namaDokter}")
                    Text(text = "Biaya : ${reservasi.biaya}")
                    Text(text = "Pembayaran : ${reservasi.jenisPembayaran}")
                    Text(text = "Status : ${reservasi.statusPembayaran}")
                }

            }
            CustomDivider()
        }
    }
}
