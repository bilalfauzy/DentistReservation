package com.example.dentistreservation.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class JadwalDokter(
    var id: String? = null,
    var tanggal: String? = null,
    var hari: String? = null,
    var jam: String? = null,
    var status: String? = null
){
    @Exclude
    fun toMap():Map<String, Any?>{
        return mapOf(
            "id" to id,
            "tanggal" to tanggal,
            "hari" to hari,
            "jam" to jam,
            "status" to status
        )
    }
}
