package com.example.dentistreservation.viewmodel.reservasi

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentistreservation.model.DokterGigi
import com.example.dentistreservation.model.JadwalDokter
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MemilihTanggalVM : ViewModel(){
    private val db = Firebase.firestore

    fun dokterByDate(date: String, jam: String): Flow<List<DokterGigi>>{

        return callbackFlow {
            val availableDokter = mutableListOf<DokterGigi>()
            db.collectionGroup("jadwal")
                .whereEqualTo("tanggal", date)
                .whereEqualTo("jam", jam)
                .get()
                .addOnSuccessListener {
                    val availableDokterId = mutableSetOf<String>()
                    for (jadwalDoc in it.documents){
                        val dokterId = jadwalDoc.reference.parent.parent?.id
                        dokterId?.let {
                            availableDokterId.add(it)
                        }
                    }

                    if (availableDokterId.isNotEmpty()){
                        db.collection("dokter")
                            .whereIn(FieldPath.documentId(), availableDokterId.toList())
                            .get()
                            .addOnSuccessListener {
                                for (dokterDoc in it.documents){
                                    val dokter = dokterDoc.toObject(DokterGigi::class.java)
                                    dokter?.let {
                                        availableDokter.add(it)
                                    }
                                }
                                trySend(availableDokter).isSuccess
                            }
                            .addOnFailureListener {
                                Log.e(TAG, "error", it)
                            }
                    }else{
                        trySend(availableDokter).isSuccess
                    }
                }
                .addOnFailureListener{
                    Log.e(TAG, "eroor cok", it)
                }
            awaitClose()
        }
    }
}