package com.example.dentistreservation.viewmodel.reservasi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dentistreservation.model.DokterGigi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MemilihDokterVM : ViewModel() {
    private val firestoreDb = Firebase.firestore
    private val dokterCollectionRef = firestoreDb.collection("doktergigi")

    fun getDokter() : LiveData<List<DokterGigi>>{
        val dokterLiveData = MutableLiveData<List<DokterGigi>>()

        dokterCollectionRef.addSnapshotListener{snapshot, error ->
            val dokterList = mutableListOf<DokterGigi>()
            if (error != null){
                Log.e("error", "Listen failed: $error")
                return@addSnapshotListener
            }

            for (doc in snapshot?.documents!!){
                val dokterGigi = doc.toObject(DokterGigi::class.java)
                dokterList.add(dokterGigi!!)
            }
            dokterLiveData.value = dokterList
        }

        return dokterLiveData
    }
}