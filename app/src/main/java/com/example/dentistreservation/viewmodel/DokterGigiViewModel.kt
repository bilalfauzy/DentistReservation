package com.example.dentistreservation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dentistreservation.model.DokterGigi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DokterGigiViewModel : ViewModel() {
    private var db = Firebase.firestore
    private val doktergigi = "doktergigi"

    val createLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val updateLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val getListLiveData: MutableLiveData<List<DokterGigi>> by lazy {
        MutableLiveData<List<DokterGigi>>()
    }

    val deleteLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun create(dokterGigi: DokterGigi){
        val docRef = db.collection(doktergigi)
        docRef.add(dokterGigi.toMap()).addOnSuccessListener {
            createLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("create", it.localizedMessage!!)
            createLiveData.postValue(false)
        }
    }

    fun update(dokterGigi: DokterGigi) {
        val docRef = db.collection(doktergigi)
        docRef.document(dokterGigi.id!!).update(dokterGigi.toMap()).addOnSuccessListener {
            updateLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("update", it.localizedMessage!!)
            updateLiveData.postValue(false)
        }
    }

    fun delete(id: String) {
        val docRef = db.collection(doktergigi)
        docRef.document(id).delete().addOnSuccessListener {
            deleteLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("delete", it.localizedMessage!!)
            deleteLiveData.postValue(false)
        }
    }

    fun getList() {
        val docRef = db.collection(doktergigi)
        docRef.get().addOnSuccessListener {
            val listDoktergigi = ArrayList<DokterGigi>()
            for (item in it.documents) {
                val dokterGigi = DokterGigi()
                dokterGigi.id = item.id
                dokterGigi.nama = item.data!!["nama"] as String?
                dokterGigi.gender = item.data!!["gender"] as String?
                dokterGigi.spesialis = item.data!!["spesialis"] as String?
                dokterGigi.umur= item.data!!["umur"] as String?
                listDoktergigi.add(dokterGigi)
            }
            getListLiveData.postValue(listDoktergigi)
        }.addOnFailureListener {
            Log.d("get", it.localizedMessage!!)
            getListLiveData.postValue(null)
        }
    }
}