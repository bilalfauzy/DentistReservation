package com.example.dentistreservation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dentistreservation.model.Users
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UsersViewModel : ViewModel() {
    private var db = Firebase.firestore
    private val userss = "users"

    val createLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val getListLiveData: MutableLiveData<List<Users>> by lazy {
        MutableLiveData<List<Users>>()
    }

    val deleteLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun create(users: Users){
        val docRef = db.collection(userss)
        docRef.add(users.toMap()).addOnSuccessListener {
            createLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("create", it.localizedMessage!!)
            createLiveData.postValue(false)
        }
    }

    fun delete(id: String) {
        val docRef = db.collection(userss)
        docRef.document(id).delete().addOnSuccessListener {
            deleteLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("delete", it.localizedMessage!!)
            deleteLiveData.postValue(false)
        }
    }

    fun getList() {
        val docRef = db.collection(userss)
        docRef.get().addOnSuccessListener {
            val listUsers = ArrayList<Users>()
            for (item in it.documents) {
                val user = Users()
                user.nama = item.data!!["nama"] as String?
                user.email = item.data!!["email"] as String?
                user.password = item.data!!["password"] as String?
                listUsers.add(user)
            }
            getListLiveData.postValue(listUsers)
        }.addOnFailureListener {
            Log.d("get", it.localizedMessage!!)
            getListLiveData.postValue(null)
        }
    }
}