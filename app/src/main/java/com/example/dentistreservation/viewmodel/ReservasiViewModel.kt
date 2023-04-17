package com.example.dentistreservation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentistreservation.model.Reservasi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ReservasiViewModel : ViewModel() {
    private val db = Firebase.firestore

    private val _reservasiList = MutableStateFlow<List<Reservasi>>(emptyList())
    val reservasiList: StateFlow<List<Reservasi>> = _reservasiList

    private val _allRes = MutableStateFlow<List<Reservasi>>(emptyList())
    val allRes: StateFlow<List<Reservasi>> = _allRes

    fun createReservasi(idUser: String, reservasi: Reservasi){
        viewModelScope.launch(Dispatchers.IO){
            db.collection("users")
                .document(idUser)
                .collection("reservasi")
                .document(reservasi.idRes!!)
                .set(reservasi)
                .await()
        }
    }

    //get reservasi by user
    fun getReservasi(emailUser: String){
        viewModelScope.launch(Dispatchers.IO){
            val snapshot = db.collectionGroup("reservasi")
                .whereEqualTo("emailUser",emailUser)
                .get()
                .await()

            val listRes = snapshot.toObjects(Reservasi::class.java)
            _reservasiList.value = listRes
        }
    }

    fun getAllReservasi(){
        viewModelScope.launch(Dispatchers.IO){
            val snapshot = db.collectionGroup("reservasi")
                .get()
                .await()

            val allList = snapshot.toObjects(Reservasi::class.java)
            _allRes.value = allList
        }
    }

}