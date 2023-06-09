package com.example.dentistreservation.view.reservasi

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dentistreservation.model.Reservasi
import com.example.dentistreservation.payment.model.PaymentResponse
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.view.customcomponent.MyButton
import com.example.dentistreservation.viewmodel.ReservasiViewModel
import com.example.dentistreservation.viewmodel.UsersViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.UUID

@Composable
fun BerhasilMembayar(
    navController: NavHostController,
    reservasiViewModel: ReservasiViewModel,
    usersViewModel: UsersViewModel,
    orderId: String,
    namaDok: String,
    tanggal: String,
    hari: String,
    jam: String,
    keluhan: String
){
    val emailLogin = FirebaseAuth.getInstance().currentUser?.email
    usersViewModel.getUserLogin(emailLogin!!)
    val userLogin by usersViewModel.userLogin.collectAsState(emptyList())

    val idUserList = userLogin.map {
        it.idUser.toString()
    }
    val namaList = userLogin.map {
        it.nama.toString()
    }

    val emailList = userLogin.map {
        it.email.toString()
    }

    val noWaList = userLogin.map {
        it.noWa.toString()
    }

    val idUser = idUserList.joinToString()
    val nama = namaList.joinToString()
    val email = emailList.joinToString()
    val noWa = noWaList.joinToString()

    val idRes = UUID.randomUUID().toString()

    val idTransaksi = remember {
        mutableStateOf("")
    }
    val jenisPembayaran = remember {
        mutableStateOf("")
    }
    val status = remember {
        mutableStateOf("")
    }
    val expire = remember {
        mutableStateOf("")
    }
    val waktuTransaksi = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column() {

        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp)
        ){
            Text(text = "id order : ${orderId}\n")
            Text(text = "id transaksi : ${idTransaksi.value}\n")
            Text(text = "jenis pembayaran : ${jenisPembayaran.value}\n")
            Text(text = "status : ${status.value}\n")
            Text(text = "expire : ${expire.value}\n")
            Text(text = "waktu transaksi : ${waktuTransaksi.value}\n")

            Text(text = "Berhasil melakukan pembayaran")

            MyButton(
                onClick = {
                    jsonParsing(
                        context,
                        orderId,
                        idTransaksi,
                        jenisPembayaran,
                        status,
                        expire,
                        waktuTransaksi
                    )
                },
                text = "CETAK FAKTUR"
            )
            if (status.value.isNotEmpty()){
                MyButton(
                    onClick = {
                        val reservasi = Reservasi(
                            idRes = idRes,
                            namaUser = nama,
                            emailUser = email,
                            noWa = noWa,
                            namaDokter = namaDok,
                            hariRes = hari,
                            tanggalRes = tanggal,
                            jamRes = jam,
                            keluhan = keluhan,
                            biaya = 50000.00,
                            jenisPembayaran = jenisPembayaran.value,
                            statusPembayaran = status.value,
                            waktuTransaksi = waktuTransaksi.value,
                            expire = expire.value
                        )
                        reservasiViewModel.createReservasi(idUser, reservasi)
                        navController.navigate(Screen.HomeScreen.route)
                    },
                    text = "OK"
                )
            }
        }
    }

}

fun jsonParsing(
    context: Context,
    orderId: String,
    idTransaksi: MutableState<String>,
    jenisPembayaran: MutableState<String>,
    status: MutableState<String>,
    expire: MutableState<String>,
    waktuTransaksi: MutableState<String>
) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://api.sandbox.midtrans.com/v2/${orderId}/status")
        .get()
        .addHeader("accept", "application/json")
        .addHeader(
            "authorization",
            "Basic U0ItTWlkLXNlcnZlci1COS0tb0w2M29FM0NSblFKd09kLWdEbGs6"
        )
        .build()

    client.newCall(request).enqueue(object: okhttp3.Callback{
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            Toast.makeText(context, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
        }

        override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
            if (response.isSuccessful){
                val body = response.body?.string()
                val gson = GsonBuilder().create()

                val parsed = gson.fromJson(body, PaymentResponse::class.java)

                val idTrans = parsed.idTransaksi.toString()
                val jenisPem = parsed.jenisPembayaran.toString()
                val stat = parsed.status.toString()
                val exp = parsed.expire.toString()
                val waktu = parsed.waktuTransaksi.toString()

                idTransaksi.value = idTrans
                jenisPembayaran.value = jenisPem
                status.value = stat
                expire.value = exp
                waktuTransaksi.value = waktu
            }
        }
    })
}
