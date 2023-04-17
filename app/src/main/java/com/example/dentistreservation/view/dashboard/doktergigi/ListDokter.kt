package com.example.dentistreservation.view.dashboard.doktergigi

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavHostController
import com.example.dentistreservation.model.DokterGigi
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.view.customcomponent.CustomDivider
import com.example.dentistreservation.view.customcomponent.MyAppBar
import com.example.dentistreservation.viewmodel.DokterGigiViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ListDokter(
    navController: NavHostController,
    dokterGigiViewModel: DokterGigiViewModel
){
    val emailLogin = FirebaseAuth.getInstance().currentUser?.email
    val listDokter by dokterGigiViewModel.dokterList.collectAsState(emptyList())
    val context = LocalContext.current

    if (emailLogin.equals("admin@gmail.com")){
        Column() {
            MyAppBar(
                title = "Dokter gigi",
                navigationIcon = Icons.Filled.ArrowBack,
                onNavigationClick = {
                    navController.navigate(Screen.AdminHomeScreen.route)
                }
            )

            MyListDokter(
                listDokter,
                onItemClick = {
                    navController.navigate(Screen.DetailDokterScreen.route)
                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }else{
        Column() {
            MyAppBar(
                title = "Dokter gigi",
                navigationIcon = Icons.Filled.ArrowBack,
                onNavigationClick = {
                    navController.navigate(Screen.HomeScreen.route)
                }
            )

            MyListDokter(
                listDokter,
                onItemClick = {
                    navController.navigate(Screen.DetailDokterScreen.route)
                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Composable
fun MyListDokter(
    items: List<DokterGigi>,
    onItemClick: (DokterGigi) -> Unit
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, bottom = 20.dp)
    ) {
        items(items) { dokter ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onItemClick(dokter) }),
                elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ){
                    Text(text = "ID dokter  : ${dokter.id}")
                    Text(text = "Nama : ${dokter.nama}")
                    Text(text = "Spesialis : ${dokter.spesialis}")
                }
            }
            CustomDivider()
        }
    }
}