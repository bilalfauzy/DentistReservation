package com.example.dentistreservation.viewmodel.reservasi

import android.app.Application
import androidx.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dentistreservation.DokuConfig
import com.example.dentistreservation.payment.DokuPayment
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class MelakukanPembayaranVM() : ViewModel() {
}


