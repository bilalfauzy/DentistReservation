package com.example.dentistreservation.view.reservasi

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dentistreservation.payment.MidtransConfig
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.view.customcomponent.MyAppBar
import com.example.dentistreservation.viewmodel.UsersViewModel
import com.example.dentistreservation.viewmodel.reservasi.MelakukanPembayaranVM
import com.google.firebase.auth.FirebaseAuth
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback
import com.midtrans.sdk.corekit.core.MidtransSDK
import com.midtrans.sdk.corekit.core.TransactionRequest
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme
import com.midtrans.sdk.corekit.models.BillingAddress
import com.midtrans.sdk.corekit.models.CustomerDetails
import com.midtrans.sdk.corekit.models.ItemDetails
import com.midtrans.sdk.corekit.models.ShippingAddress
import com.midtrans.sdk.uikit.SdkUIFlowBuilder

@SuppressLint("RememberReturnType")
@Composable
fun MelakukanPembayaran(
    navController: NavHostController,
    usersViewModel: UsersViewModel,
    namaDok: String,
    tanggal: String,
    hari: String,
    jam: String,
    keluhan: String
){
    val context = LocalContext.current
    val emailLogin = FirebaseAuth.getInstance().currentUser?.email
    usersViewModel.getUserLogin(emailLogin!!)
    val userLogin by usersViewModel.userLogin.collectAsState(emptyList())

    val namaList = userLogin.map {
        it.nama.toString()
    }

    val emailList = userLogin.map {
        it.email.toString()
    }

    val noWaList = userLogin.map {
        it.noWa.toString()
    }

    val nama = namaList.joinToString()
    val email = emailList.joinToString()
    val noWa = noWaList.joinToString()

    SdkUIFlowBuilder.init()
        .setClientKey(MidtransConfig.CLIENT_KEY) // client_key is mandatory
        .setContext(context) // context is mandatory
        .setTransactionFinishedCallback(TransactionFinishedCallback {

        }) // set transaction finish callback (sdk callback)
        .setMerchantBaseUrl(MidtransConfig.SERVER) //set merchant url (required)
        .enableLog(true) // enable sdk log (optional)
        .setColorTheme(CustomColorTheme("#FFE51255", "#B61548", "#FFE51255")) // set theme. it will replace theme on snap theme on MAP ( optional)
        .setLanguage("id") //`en` for English and `id` for Bahasa
        .buildSDK()

    Column {
        val biaya = 50000.00

        MyAppBar(
            title = "Pembayaran",
            navigationIcon = Icons.Filled.ArrowBack,
            onNavigationClick = {
                navController.navigate(Screen.MemilihTanggalScreen.route)
            }
        )
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp)
        ){
            Text(text = "Pilih metode pembayaran")

            Text(text = "Detail Reservasi :\n")
            Text(text = "Nama : ${nama}\n")
            Text(text = "Email : ${email}\n")
            Text(text = "Nomor wa : ${noWa}\n")

            Text(text = "Nama dokter : ${namaDok}\n")
            Text(text = "Tanggal : ${hari}/${tanggal}\n")
            Text(text = "Jam : ${jam}\n")
            Text(text = "Keluhan/penyakit : ${keluhan}\n")

            Row() {
                Text(text = "Shopeepay")
                Text(text = "Gopay")
            }

            Text(text = "Biaya total : ${biaya}\n")
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val orderId = "Pesanan${System.currentTimeMillis().toShort()}"

                    val transactionRequest = TransactionRequest(orderId, biaya)
                    val details = ItemDetails("ID-${namaDok}", biaya, 1, namaDok)
                    val itemDetails = ArrayList<ItemDetails>()
                    itemDetails.add(details)
                    customerDetail(
                        transactionRequest,
                        nama,
                        email,
                        noWa
                    )
                    transactionRequest.itemDetails = itemDetails
                    MidtransSDK.getInstance().transactionRequest = transactionRequest
                    MidtransSDK.getInstance().startPaymentUiFlow(context)
                    navController.navigate(Screen.BerhasilMembayarScreen.route + "/${orderId}")

                }
            ) {
                Text(text = "Bayar")
            }

        }
    }
}

fun customerDetail(
    transactionRequest: TransactionRequest,
    namaUser: String,
    email: String,
    noWa: String
){
    val customerDetails = CustomerDetails()
    customerDetails.customerIdentifier = namaUser
    customerDetails.phone = noWa
    customerDetails.firstName = namaUser
    customerDetails.email = email

    val shippingAddress = ShippingAddress()
    shippingAddress.address = null
    shippingAddress.city = null
    shippingAddress.postalCode = null

    customerDetails.shippingAddress = shippingAddress

    val billingAddress = BillingAddress()
    billingAddress.address = null
    billingAddress.city = null
    billingAddress.postalCode = null

    customerDetails.billingAddress = billingAddress

    transactionRequest.customerDetails = customerDetails

}