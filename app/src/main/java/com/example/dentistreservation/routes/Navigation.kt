package com.example.dentistreservation.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
import com.example.dentistreservation.viewmodel.reservasi.MemilihDokterVM
import com.example.dentistreservation.viewmodel.reservasi.MemilihTanggalVM

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
            MemilihDokter(navController = navController, memilihDokterVM = MemilihDokterVM())
        }

        composable(
            route = Screen.MemilihTanggalScreen.route + "/{id}/{nama}/{gender}/{spesialis}/{umur}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                },
                navArgument("nama"){
                    type = NavType.StringType
                },
                navArgument("gender"){
                    type = NavType.StringType
                },
                navArgument("spesialis"){
                    type = NavType.StringType
                },
                navArgument("umur"){
                    type = NavType.StringType
                }
            )
        ){
            val id = it.arguments?.getString("id")!!
            val nama = it.arguments?.getString("nama")!!
            val gender = it.arguments?.getString("gender")!!
            val spesialis = it.arguments?.getString("spesialis")!!
            val umur = it.arguments?.getString("umur")!!
            MemilihTanggal(
                navController = navController,
                memilihTanggalVM = MemilihTanggalVM(),
                idDok = id,
                namaDok = nama,
                genderDok = gender,
                spesialis = spesialis,
                umurDok = umur
            )
        }

        composable(route = Screen.MelakukanPembayaranScreen.route){
            MelakukanPembayaran()
        }

        composable(route = Screen.BerhasilMembayarScreen.route){
            BerhasilMembayar()
        }
    }
}