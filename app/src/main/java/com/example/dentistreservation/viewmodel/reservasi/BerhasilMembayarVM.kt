package com.example.dentistreservation.viewmodel.reservasi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class BerhasilMembayarVM: ViewModel(){
    private val _paymentResponse = MutableLiveData<String>()
    val paymentResponse: LiveData<String>
        get() = _paymentResponse

    fun getTransactionStatus(orderId: String) {
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

        val response = client.newCall(request)

        Log.e("respons", _paymentResponse.value.toString())
    }
}