package com.example.dentistreservation.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dentistreservation.view.dashboard.Home
import com.example.dentistreservation.view.dashboard.ListReservasi
import com.example.dentistreservation.view.dashboard.Profile
import com.example.dentistreservation.view.loginregister.Login
import com.example.dentistreservation.view.loginregister.Register
import com.example.dentistreservation.view.reservasi.BerhasilMembayar
import com.example.dentistreservation.view.reservasi.MelakukanPembayaran
import com.example.dentistreservation.view.reservasi.MemilihDokter
import com.example.dentistreservation.view.reservasi.MemilihTanggal
import com.example.dentistreservation.viewmodel.loginregister.LoginViewModel
import com.example.dentistreservation.viewmodel.loginregister.RegisterViewModel

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(route = Screen.LoginScreen.route){
            Login(navController = navController, loginViewModel = LoginViewModel())
        }

        composable(
            route = Screen.HomeScreen.route
        ){
            Home(navController = navController)
        }
        
        composable(route = Screen.RegisterScreen.route){
            Register(navController = navController ,registerViewModel = RegisterViewModel())
        }

        composable(route = Screen.ProfileScreen.route){
            Profile()
        }

        composable(route = Screen.ListReservasiScreen.route){
            ListReservasi(navController = navController)
        }

        composable(route = Screen.MemilihDokterScreen.route){
            MemilihDokter()
        }

        composable(route = Screen.MemilihTanggalScreen.route){
            MemilihTanggal()
        }

        composable(route = Screen.MelakukanPembayaranScreen.route){
            MelakukanPembayaran()
        }

        composable(route = Screen.BerhasilMembayarScreen.route){
            BerhasilMembayar()
        }


    }
}