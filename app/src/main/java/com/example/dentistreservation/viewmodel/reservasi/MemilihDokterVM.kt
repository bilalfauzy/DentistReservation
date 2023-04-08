package com.example.dentistreservation.viewmodel.reservasi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentistreservation.model.DokterGigi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MemilihDokterVM : ViewModel() {
    private val db = Firebase.firestore

    private val _dokterList = MutableStateFlow<List<DokterGigi>>(emptyList())
    val dokterList: StateFlow<List<DokterGigi>> = _dokterList
    private val _listId = MutableStateFlow<List<String>>(emptyList())
    val listId: StateFlow<List<String>> = _listId

    init {
        viewModelScope.launch {
            try {
                val snapshot = db.collection("dokter")
                    .get().await()
                for (doc in snapshot.documents) {
                    val idDokter = doc.id
                    _listId.value = listOf(idDokter)
                    Log.e("ID", "${_listId}")
                }
                val dokterList = snapshot.toObjects<DokterGigi>()
                _dokterList.value = dokterList
            } catch (e: Exception) {
                Log.e("DokterViewModel", "Error getting dokter", e)
            }
        }
    }

}