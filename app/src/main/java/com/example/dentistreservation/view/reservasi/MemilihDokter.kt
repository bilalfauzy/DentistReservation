package com.example.dentistreservation.view.reservasi

import androidx.compose.foundation.layout.Column
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
import com.example.dentistreservation.viewmodel.reservasi.MemilihDokterVM

val localLifecycleOwner = staticCompositionLocalOf<LifecycleOwner?> { null }

@Composable
fun MemilihDokter(
    navController: NavHostController,
    memilihDokterVM: MemilihDokterVM = viewModel()
) {
    val dokterList = remember {
        mutableListOf(emptyList<DokterGigi>())
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = memilihDokterVM){
        memilihDokterVM.getDokter().
    }
}

@Composable
fun MyListDokter(dokterList : List<DokterGigi>) {
    LazyColumn(){
        items(dokterList){
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 4.dp,
                modifier = Modifier.padding(8.dp)
            ){
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text ="ID : ${it.id}")
                    Text(text ="Nama : ${it.nama}")
                    Text(text ="Gender : ${it.gender}")
                    Text(text ="Spesialis : ${it.spesialis}")
                    Text(text ="Umur : ${it.umur}")
                }
            }
        }
    }
}
