package com.example.dentistreservation.view.reservasi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.dentistreservation.model.DokterGigi
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.viewmodel.reservasi.MemilihDokterVM

@Composable
fun MemilihDokter(
    navController: NavHostController,
    memilihDokterVM: MemilihDokterVM = viewModel()
) {
    val dokterListState by memilihDokterVM.dokterList.collectAsState(emptyList())
    MyListDokter(
        dokterList = dokterListState,
        navController
    )
}

@Composable
fun MyListDokter(dokterList : List<DokterGigi>, navController: NavHostController) {
    LazyColumn(){
        items(dokterList){ dokter ->
            DokterItems(dokter = dokter, onItemClick = {
                navController.navigate(Screen.CreateJadwalScreen.route +
                        "/${it.id}/${it.nama}/${it.gender}/${it.spesialis}/${it.umur}")
            })
        }
    }
}

@Composable
fun DokterItems(
    dokter: DokterGigi,
    onItemClick: (DokterGigi) -> Unit
){
    Card(
        modifier = Modifier.padding(16.dp)
            .fillMaxWidth()
            .clickable { onItemClick(dokter) },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID : ${dokter.id}")
            Text(text = "Nama : ${dokter.nama}")
            Text(text = "Gender : ${dokter.gender}")
            Text(text = "Spesialis : ${dokter.spesialis}")
            Text(text = "Umur : ${dokter.umur}")
        }
    }
}
