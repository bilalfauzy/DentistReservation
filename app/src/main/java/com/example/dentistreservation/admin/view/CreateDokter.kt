package com.example.dentistreservation.admin.view

import android.app.DatePickerDialog
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.dentistreservation.admin.viewmodel.CreateDokterVM
import com.example.dentistreservation.model.DokterGigi
import com.example.dentistreservation.model.JadwalDokter
import com.example.dentistreservation.view.customcomponent.CustomExposedDropdown
import com.example.dentistreservation.view.customcomponent.MyAppBar
import java.time.LocalDate
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateDokter(createDokterVM: CreateDokterVM){
    val idDok = remember {
        mutableStateOf("")
    }
    val nama = remember {
        mutableStateOf("")
    }
    val gender = remember {
        mutableStateOf<String?>(null)
    }
    val listGender = listOf(
        "Laki - laki",
        "Perempuan",
    )
    val spesialis = remember {
        mutableStateOf("")
    }
    val umur = remember {
        mutableStateOf("")
    }

    val idJadwal = remember {
        mutableStateOf("")
    }
    val hari = remember {
        mutableStateOf<String?>(null)
    }
    val listHari = listOf(
        "Senin",
        "Selasa",
        "Rabu",
        "Kamis",
        "Jumat",
        "Sabtu"
    )

    val jam = remember {
        mutableStateOf<String?>(null)
    }
    val listJam = listOf(
        "08.00",
        "10.00",
        "13.00",
        "15.00"
    )

    val status = remember {
        mutableStateOf<String?>(null)
    }
    val listStatus = listOf(
        "Tersedia",
        "Kosong"
    )

    val selectedDate = remember {
        mutableStateOf(LocalDate.now())
    }

    val context = LocalContext.current

    Column {
        MyAppBar(
            title = "Menambah dokter",
            navigationIcon = Icons.Filled.ArrowBack,
            onNavigationClick = {

            }
        )

        OutlinedTextField(
            value = idDok.value,
            onValueChange = {
                idDok.value = it
            },
            label = {
                Text("ID dokter")
            },
            modifier = Modifier.fillMaxWidth()
        )

        //data dokter
        OutlinedTextField(
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            label = {
                Text("Nama dokter")
            },
            modifier = Modifier.fillMaxWidth()
        )

        //gender dokter
        CustomExposedDropdown(options = listGender, label = "Pilih gender", onOptionSelected = {
            gender.value = it
        }, selectedOption = gender.value)

        OutlinedTextField(
            value = spesialis.value,
            onValueChange = {
                spesialis.value = it
            },
            label = {
                Text("Spesialis")
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = umur.value,
            onValueChange = {
                umur.value = it
            },
            label = {
                Text("Umur")
            },
            modifier = Modifier.fillMaxWidth()
        )

        if (
            idDok.value.isNotEmpty()
            && nama.value.isNotEmpty()
            && gender.value!!.isNotEmpty()
            && spesialis.value.isNotEmpty()
            && umur.value.isNotEmpty()
        ){
            //id jadwal
            OutlinedTextField(
                value = idJadwal.value,
                onValueChange = {
                    idJadwal.value = it
                },
                label = {
                    Text("ID Jadwal")
                },
                modifier = Modifier.fillMaxWidth()
            )
            //tanggal
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = selectedDate.value.toString(),
                    onValueChange = {
                        selectedDate.value  = LocalDate.parse(it)
                    },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                val calendar = Calendar.getInstance()
                                val year = calendar.get(Calendar.YEAR)
                                val month = calendar.get(Calendar.MONTH)
                                val day = calendar.get(Calendar.DAY_OF_MONTH)

                                val datepicker = DatePickerDialog(
                                    context,
                                    { _, year, month, dayOfMonth ->
                                        selectedDate.value = LocalDate.of(year, month +1, dayOfMonth)
                                    },
                                    year, month, day
                                )
                                datepicker.datePicker.minDate = calendar.timeInMillis
                                datepicker.show()
                            },
                            modifier = Modifier.offset(10.dp)
                        ) {
                            Icon(imageVector = Icons.Outlined.DateRange, contentDescription = "Pilih tanggal")
                        }
                    }
                )
            }

            //hari
            CustomExposedDropdown(options = listHari, label = "Pilih hari", onOptionSelected = {
                hari.value = it
            }, selectedOption = hari.value)


            //jam
            CustomExposedDropdown(options = listJam, label = "Pilih jam", onOptionSelected = {
                jam.value = it
            }, selectedOption = jam.value)

            //status
            CustomExposedDropdown(options = listStatus, label = "Status", onOptionSelected = {
                status.value = it
            }, selectedOption = status.value)

        }

        Button(
            onClick = {
                val dokterGigi = DokterGigi(
                    id = idDok.value,
                    nama = nama.value,
                    gender = gender.value,
                    spesialis = spesialis.value,
                    umur = umur.value
                )

                val jadwalDokter = JadwalDokter(
                    id = idJadwal.value,
                    tanggal = selectedDate.value.toString(),
                    hari = hari.value,
                    jam = jam.value,
                    status = status.value
                )

                createDokterVM.createDokter(dokterGigi, jadwalDokter)
                Toast.makeText(context, "Berhasil menambahkan data", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(text = "Simpan")
        }
    }
}