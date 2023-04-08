package com.example.dentistreservation.admin.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentistreservation.model.DokterGigi
import com.example.dentistreservation.model.JadwalDokter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CreateJadwalVM : ViewModel() {
    private val db = Firebase.firestore
    fun createJadwal(idDokter: String, jadwalDokter: JadwalDokter){
        viewModelScope.launch(Dispatchers.IO){
            db.collection("dokter")
                .document(idDokter)
                .collection("jadwal")
                .document(jadwalDokter.id!!)
                .set(jadwalDokter)
                .await()
        }
    }
}