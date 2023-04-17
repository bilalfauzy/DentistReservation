package com.example.dentistreservation.admin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentistreservation.model.DokterGigi
import com.example.dentistreservation.model.JadwalDokter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CreateDokterVM : ViewModel(){
    private val db = Firebase.firestore

    fun createDokter(dokterGigi: DokterGigi, jadwalDokter: JadwalDokter){
        viewModelScope.launch(Dispatchers.IO){
            db.collection("dokter").document(dokterGigi.id!!)
                .set(dokterGigi)
                .addOnSuccessListener {
                    db.collection("dokter").document(dokterGigi.id!!).collection("jadwal")
                        .document(jadwalDokter.id!!)
                        .set(jadwalDokter)
                }
                .await()
        }
    }

    fun deleteDokter(dokterGigi: DokterGigi){
        viewModelScope.launch(Dispatchers.IO){
            db.collection("dokter")
                .document(dokterGigi.id!!)
                .delete()
                .await()
        }
    }
}