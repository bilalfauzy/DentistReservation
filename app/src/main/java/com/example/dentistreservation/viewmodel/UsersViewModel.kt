package com.example.dentistreservation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentistreservation.model.DokterGigi
import com.example.dentistreservation.model.JadwalDokter
import com.example.dentistreservation.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UsersViewModel : ViewModel() {
    private var db = Firebase.firestore

    private val _userLogin = MutableStateFlow<List<Users>>(emptyList())
    val userLogin: StateFlow<List<Users>> = _userLogin

    fun getUserLogin(emailUser: String) {

        if (emailUser.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val snapshot = db.collection("users")
                        .whereEqualTo("email", emailUser)
                        .get()
                        .await()
                    val user = snapshot.toObjects<Users>()
                    _userLogin.value = user
                } catch (e: Exception) {
                    Log.e("UserLogin", "Gagal mengambil user login", e)
                }
            }
        }

        fun getUser(users: Users) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val snapshot = db.collection("users")
                        .get().await()
                    val users = snapshot.toObjects<Users>()
                } catch (e: Exception) {
                    Log.e("UserViewModel", "Error getting users", e)
                }

            }
        }
    }
}