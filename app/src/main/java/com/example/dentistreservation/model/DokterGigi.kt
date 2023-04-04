package com.example.dentistreservation.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class DokterGigi(
    var id: String? = null,
    var nama: String? = null,
    var gender: String? = null,
    var spesialis: String? = null,
    var umur: String? = null,
    var jadwal: Map<String, List<String>>? = null
){
    @Exclude
    fun toMap():Map<String, Any?>{
        return mapOf(
            "id" to id,
            "nama" to nama,
            "gender" to gender,
            "spesialis" to spesialis,
            "umur" to umur,
            "jadwal" to jadwal
        )
    }
}
