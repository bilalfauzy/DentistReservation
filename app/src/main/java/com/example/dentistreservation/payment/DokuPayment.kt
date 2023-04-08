package com.example.dentistreservation.payment

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dentistreservation.DokuConfig
import org.json.JSONObject
import java.util.UUID

class DokuPayment(private val context: Context) {
    fun getPaymentUrl(
        name: String,
        price: Int,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val url = "https://api-sandbox.doku.com/shopeepay-emoney/v2/order"
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.POST,
            url,
            null,
            Response.Listener { response ->
                val paymentUrl = response.getJSONObject("data").getString("paymentUrl")
                onSuccess(paymentUrl)
            },
            Response.ErrorListener { error ->
                onError(error.message ?: "An error occurred.")
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["MALLID"] = "MallID"
                headers["SHARED-KEY"] = "SharedKey"
                headers["REQUEST-TIMESTAMP"] = getCurrentTimestamp().toString()
                return headers
            }

            override fun getBody(): ByteArray {
                val requestBody = JSONObject()
                requestBody.put("id", DokuConfig.MALL_ID)
                requestBody.put("amount", price.toString())
                requestBody.put("invoice", UUID.randomUUID().toString())
                requestBody.put("email", "test@example.com")
                requestBody.put("name", name)
                return requestBody.toString().toByteArray(Charsets.UTF_8)
            }
        }
        requestQueue.add(jsonObjectRequest)
    }

    private fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis() / 1000
    }
}
