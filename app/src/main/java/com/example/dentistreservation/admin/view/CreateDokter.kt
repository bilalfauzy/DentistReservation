package com.example.dentistreservation.admin.view

import android.app.DatePickerDialog
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.dentistreservation.admin.viewmodel.CreateDokterVM
import com.example.dentistreservation.model.DokterGigi
import com.example.dentistreservation.model.JadwalDokter
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.view.MyAppBar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
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
        mutableStateOf("")
    }
    val listGender = listOf(
        "Laki - laki",
        "Perempuan"
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
        mutableStateOf("")
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
        mutableStateOf("")
    }
    val listJam = listOf(
        "08.00",
        "10.00",
        "13.00",
        "15.00"
    )

    val status = remember {
        mutableStateOf("")
    }
    val listStatus = listOf(
        "Tersedia",
        "Kosong"
    )

    var mTextFieldSize = remember {
        mutableStateOf(Size.Zero)
    }

    var mExpanded = remember {
        mutableStateOf(false)
    }

    val icon  = if (mExpanded.value){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    val selectedDate = remember {
        mutableStateOf(LocalDate.now())
    }

    val context = LocalContext.current

    Column() {
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
        OutlinedTextField(
            value = gender.value,
            onValueChange = {
                gender.value = it
            },
            label = {
                Text("Gender")
            },
            modifier = Modifier.fillMaxWidth()
                .onGloballyPositioned {
                    mTextFieldSize.value = it.size.toSize()
                },
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { mExpanded.value = true })
            }
        )
        DropdownMenu(
            expanded = mExpanded.value,
            onDismissRequest = { mExpanded.value = false }
        ) {
            listGender.forEach {
                DropdownMenuItem(onClick = {
                    gender.value = it
                    mExpanded.value = false
                }){
                    Text(text = "${it}")
                }
            }
        }


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
            && gender.value.isNotEmpty()
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
                                    { view, year, month, dayOfMonth ->
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
            OutlinedTextField(
                value = hari.value,
                onValueChange = {
                    hari.value = it
                },
                label = {
                    Text("Hari kerja")
                },
                modifier = Modifier.fillMaxWidth()
                    .onGloballyPositioned {
                        mTextFieldSize.value = it.size.toSize()
                    },
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { mExpanded.value = true })
                }
            )
            DropdownMenu(
                expanded = mExpanded.value,
                onDismissRequest = { mExpanded.value = false }
            ) {
                listHari.forEach {
                    DropdownMenuItem(onClick = {
                        hari.value = it
                        mExpanded.value = false
                    }){
                        Text(text = "${it}")
                    }
                }
            }

            //jam
            OutlinedTextField(
                value = jam.value,
                onValueChange = {
                    jam.value = it
                },
                label = {
                    Text("jam kerja")
                },
                modifier = Modifier.fillMaxWidth()
                    .onGloballyPositioned {
                        mTextFieldSize.value = it.size.toSize()
                    },
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { mExpanded.value = true })
                }
            )
            DropdownMenu(
                expanded = mExpanded.value,
                onDismissRequest = { mExpanded.value = false }
            ) {
                listJam.forEach {
                    DropdownMenuItem(onClick = {
                        jam.value = it
                        mExpanded.value = false
                    }){
                        Text(text = "${it}")
                    }
                }
            }

            //status
            OutlinedTextField(
                value = status.value,
                onValueChange = {
                    status.value = it
                },
                label = {
                    Text("Status")
                },
                modifier = Modifier.fillMaxWidth()
                    .onGloballyPositioned {
                        mTextFieldSize.value = it.size.toSize()
                    },
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        Modifier.clickable { mExpanded.value = true })
                }
            )
            DropdownMenu(
                expanded = mExpanded.value,
                onDismissRequest = { mExpanded.value = false }
            ) {
                listStatus.forEach {
                    DropdownMenuItem(onClick = {
                        status.value = it
                        mExpanded.value = false
                    }){
                        Text(text = "${it}")
                    }
                }
            }

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