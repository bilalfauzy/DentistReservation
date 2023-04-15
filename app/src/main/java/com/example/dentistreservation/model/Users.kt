package com.example.dentistreservation.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties


@IgnoreExtraProperties
data class Users(
    var idUser: String? = null,
    var nama: String? = null,
    var umur: Int = 0,
    var gender: String? = null,
    var noWa: String? = null,
    var email: String? = null,
    var password: String? = null
){
    @Exclude
    fun toMap():Map<String, Any?>{
        return mapOf(
            "nama" to nama,
            "umur" to umur,
            "gender" to gender,
            "email" to email,
            "password" to password
        )
    }
}
