package com.example.dentistreservation.view.dashboard

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.dentistreservation.routes.Screen

@Composable
fun ListReservasi(navController: NavHostController){
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    MyListReservasi(items)
    Button(
        onClick = {
            navController.navigate(Screen.HomeScreen.route)
        }
    ) {
        Text(text = "OK")
    }
}

@Composable
fun MyListReservasi(items: List<String>) {
    LazyColumn(){
        for (item in items){
            items(items){
                Text(text = item)
            }
        }
    }
}