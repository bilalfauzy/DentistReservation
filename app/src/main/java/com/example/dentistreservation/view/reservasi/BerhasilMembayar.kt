package com.example.dentistreservation.view.reservasi

import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.example.dentistreservation.payment.model.PaymentResponse
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.view.customcomponent.MyAppBar
import com.example.dentistreservation.view.customcomponent.MyButton
import com.example.dentistreservation.viewmodel.reservasi.BerhasilMembayarVM
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

@Composable
fun BerhasilMembayar(
    navController: NavHostController,
    berhasilMembayarVM: BerhasilMembayarVM,
    orderId: String
){
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
            MyButton(
                onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                },
                text = "OK"
            )
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
