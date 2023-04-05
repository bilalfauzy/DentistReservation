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

    private val _jadwalList = MutableStateFlow<List<JadwalDokter>>(emptyList())
    val jadwalList: StateFlow<List<JadwalDokter>> = _jadwalList

    private val _dokters = MutableStateFlow<List<DokterGigi>>(emptyList())
    val dokters: StateFlow<List<DokterGigi>> get() = _dokters


    fun dokterByDate(date: String): Flow<List<DokterGigi>>{

        return callbackFlow {
            val availableDokter = mutableListOf<DokterGigi>()
            db.collectionGroup("jadwal")
                .whereEqualTo("tanggal", date)
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
    init {
        viewModelScope.launch {
            try {
                val snapshot = db.collection("jadwal")
                    .get()
                    .await()

                val jadwalList = snapshot.toObjects<JadwalDokter>()
                _jadwalList.value = jadwalList
            } catch (e: Exception) {
                Log.e("DokterViewModel", "Error getting dokter", e)
            }
        }
    }

}