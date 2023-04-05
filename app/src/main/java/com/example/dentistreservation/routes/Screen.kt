package com.example.dentistreservation.routes

sealed class Screen(val route: String){
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object HomeScreen : Screen("home_screen")
    object ProfileScreen : Screen("profile_screen")
    object ListReservasiScreen : Screen("listreservasi_screen")
    object MemilihDokterScreen : Screen("memilihdokter_screen")
    object MemilihTanggalScreen : Screen("memilihtanggal_screen")
    object MelakukanPembayaranScreen : Screen("melakukanpembayaran_screen")
    object BerhasilMembayarScreen : Screen("berhasilmembayar_screen")

    object CreateDokterScreen : Screen("createdokter_screen")

    object CreateJadwalScreen : Screen("createjadwal_screen")


}
