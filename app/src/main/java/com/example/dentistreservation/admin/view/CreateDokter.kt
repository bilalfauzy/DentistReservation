package com.example.dentistreservation.admin.view

import android.app.DatePickerDialog
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dentistreservation.R
import com.example.dentistreservation.admin.viewmodel.CreateDokterVM
import com.example.dentistreservation.model.DokterGigi
import com.example.dentistreservation.model.JadwalDokter
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.ui.theme.backColor
import com.example.dentistreservation.view.customcomponent.*
import java.time.LocalDate
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateDokter(
    navController: NavHostController,
    createDokterVM: CreateDokterVM
){
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

    var isError = false

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.scrollable(scrollState, Orientation.Vertical)
    ){
        MyAppBar(
            title = "Menambah dokter",
            navigationIcon = Icons.Filled.ArrowBack,
            onNavigationClick = {
                navController.navigate(Screen.AdminHomeScreen.route)
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(backColor)
        ) {

            //id dokter
            CustomTextField(
                value = idDok.value,
                onValueChange = {
                    idDok.value = it
                    isError = it.isEmpty()
                },
                label = "ID dokter",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "ID",
                        tint = MaterialTheme.colors.primary
                    )
                },
                isError = isError
            )

            //data dokter
            CustomTextField(
                value = nama.value,
                onValueChange = {
                    nama.value = it
                    isError = it.isEmpty()
                },
                label = "Nama dokter",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "ID",
                        tint = MaterialTheme.colors.primary
                    )
                },
                isError = isError
            )

            //gender dokter
            CustomExposedDropdown(options = listGender, label = "Pilih gender", onOptionSelected = {
                gender.value = it
            }, selectedOption = gender.value)

            //spesialis dokter
            CustomTextField(
                value = spesialis.value,
                onValueChange = {
                    spesialis.value = it
                    isError = it.isEmpty()
                },
                label = "Spesialis",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "ID",
                        tint = MaterialTheme.colors.primary
                    )
                },
                isError = isError
            )

            //umur dokter
            CustomTextField(
                value = umur.value,
                onValueChange = {
                    umur.value = it
                    isError = it.isEmpty()
                },
                label = "Umur",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "ID",
                        tint = MaterialTheme.colors.primary
                    )
                },
                isError = isError
            )

            //id jadwal
            CustomTextField(
                value = idJadwal.value,
                onValueChange = {
                    idJadwal.value = it
                    isError = it.isEmpty()
                },
                label = "ID jadwal",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "ID",
                        tint = MaterialTheme.colors.primary
                    )
                },
                isError = isError
            )

            CustomSpacer()
            //tanggal
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = selectedDate.value.toString(),
                    onValueChange = {
                        selectedDate.value = LocalDate.parse(it)
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
                                        selectedDate.value =
                                            LocalDate.of(year, month + 1, dayOfMonth)
                                    },
                                    year, month, day
                                )
                                datepicker.datePicker.minDate = calendar.timeInMillis
                                datepicker.show()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.DateRange,
                                contentDescription = "Pilih tanggal"
                            )
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

            CustomSpacer()
            CustomSpacer()
            CustomSpacer()
            CustomSpacer()
            MyButton(
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
                },
                text = "SIMPAN"
            )
        }
    }
}