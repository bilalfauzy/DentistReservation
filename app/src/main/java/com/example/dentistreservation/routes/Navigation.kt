package com.example.dentistreservation.routes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dentistreservation.admin.view.AdminHome
import com.example.dentistreservation.admin.view.CreateDokter
import com.example.dentistreservation.admin.view.CreateJadwal
import com.example.dentistreservation.admin.viewmodel.CreateDokterVM
import com.example.dentistreservation.admin.viewmodel.CreateJadwalVM
import com.example.dentistreservation.view.dashboard.Home
import com.example.dentistreservation.view.dashboard.ListReservasi
import com.example.dentistreservation.view.dashboard.Profile
import com.example.dentistreservation.view.loginregister.Login
import com.example.dentistreservation.view.loginregister.Register
import com.example.dentistreservation.view.reservasi.BerhasilMembayar
import com.example.dentistreservation.view.reservasi.MelakukanPembayaran
import com.example.dentistreservation.view.reservasi.MemilihDokter
import com.example.dentistreservation.view.reservasi.MemilihTanggal
import com.example.dentistreservation.viewmodel.UsersViewModel
import com.example.dentistreservation.viewmodel.loginregister.LoginViewModel
import com.example.dentistreservation.viewmodel.loginregister.RegisterViewModel
import com.example.dentistreservation.viewmodel.reservasi.BerhasilMembayarVM
import com.example.dentistreservation.viewmodel.reservasi.MelakukanPembayaranVM
import com.example.dentistreservation.viewmodel.reservasi.MemilihDokterVM
import com.example.dentistreservation.viewmodel.reservasi.MemilihTanggalVM

@RequiresApi(Build.VERSION_CODES.O)
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
            route = Screen.MemilihTanggalScreen.route
        ){
            MemilihTanggal(
                navController = navController,
                memilihTanggalVM = MemilihTanggalVM(),
                memilihDokterVM = MemilihDokterVM()
            )
        }

        composable(route = Screen.MelakukanPembayaranScreen.route + "/{nama}/{tanggal}/{hari}/{jam}/{keluhan}",
            arguments = listOf(
                navArgument("nama"){
                    type = NavType.StringType
                },
                navArgument("tanggal"){
                    type = NavType.StringType
                },
                navArgument("hari"){
                    type = NavType.StringType
                },
                navArgument("jam"){
                    type = NavType.StringType
                },
                navArgument("keluhan"){
                    type = NavType.StringType
                }
            )
        ){
            val nama = it.arguments?.getString("nama")!!
            val tanggal = it.arguments?.getString("tanggal")!!
            val hari = it.arguments?.getString("hari")!!
            val jam = it.arguments?.getString("jam")!!
            val keluhan = it.arguments?.getString("keluhan")!!
            MelakukanPembayaran(
                navController = navController,
                usersViewModel = UsersViewModel(),
                namaDok = nama,
                tanggal = tanggal,
                hari = hari,
                jam = jam,
                keluhan = keluhan
            )
        }

        composable(
            route = Screen.BerhasilMembayarScreen.route + "/{orderId}",
            arguments = listOf(
                navArgument("orderId"){
                    type = NavType.StringType
                }
            )
        ){
            val orderId = it.arguments?.getString("orderId")!!
            BerhasilMembayar(
                navController = navController,
                berhasilMembayarVM = BerhasilMembayarVM(),
                orderId = orderId
            )
        }

        composable(route = Screen.CreateDokterScreen.route){
            CreateDokter(createDokterVM = CreateDokterVM())
        }

        composable(
            route = Screen.CreateJadwalScreen.route
        ){
            CreateJadwal(
                createJadwalVM = CreateJadwalVM(),
                memilihDokterVM = MemilihDokterVM()
            )
        }
        
        //admin
        composable(route = Screen.AdminHomeScreen.route){
            AdminHome(navController = navController)
        }
    }
}