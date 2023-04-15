package com.example.dentistreservation.payment

import com.example.dentistreservation.payment.model.PaymentResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("status")
    fun getStatusTransaksi(): Call<PaymentResponse?>?
}