package com.example.dentistreservation.admin.view


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
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.view.customcomponent.MyAppBar

@Composable
fun AdminHome(
    navController: NavHostController
){
    Column() {
        MyAppBar(
            title = "Home",
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
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate(Screen.CreateDokterScreen.route)
                }
            ) {
                Text(text = "Tambah dokter")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate(Screen.CreateJadwalScreen.route)
                }
            ) {
                Text(text = "Tambah jadwal")
            }
        }
    }
}