package com.example.dentistreservation.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Reservasi(
    var id: String? = null,
    var namaDokter : String? = null,
    var tanggalRes: String? = null,
    var keluhan : String? = null,
    var biaya: Double? = null,
    var tanggalCreate : Timestamp? = null
){
    @Exclude
    fun toMap():Map<String, Any?>{
        return mapOf(
            "id" to id,
            "namaDokter" to namaDokter,
            "tanggalReservasi" to tanggalRes,
            "keluhan" to keluhan,
            "biaya" to biaya,
            "tanggalCreate" to tanggalCreate
        )
    }
}
