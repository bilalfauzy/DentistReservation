package com.example.dentistreservation.admin.view

import android.app.DatePickerDialog
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dentistreservation.R
import com.example.dentistreservation.admin.viewmodel.CreateJadwalVM
import com.example.dentistreservation.model.JadwalDokter
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.ui.theme.backColor
import com.example.dentistreservation.view.customcomponent.*
import com.example.dentistreservation.viewmodel.DokterGigiViewModel
import java.time.LocalDate
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateJadwal(
    navController: NavHostController,
    createJadwalVM: CreateJadwalVM,
    dokterGigiViewModel: DokterGigiViewModel
) {

    val dokterList by dokterGigiViewModel.dokterList.collectAsState(emptyList())
    val listId = dokterList.map {
        it.id.toString()
    }

    val idDok = remember {
        mutableStateOf<String?>(null)
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

    Column {
        MyAppBar(
            title = "Menambah jadwal",
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
            CustomExposedDropdown(
                options = listId,
                label = "Pilih ID dokter",
                onOptionSelected = {
                    idDok.value = it
                },
                selectedOption = idDok.value
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
            CustomExposedDropdown(options = listStatus, label = "Pilih status", onOptionSelected = {
                status.value = it
            }, selectedOption = status.value)

            CustomSpacer()
            CustomSpacer()
            CustomSpacer()
            CustomSpacer()
            MyButton(
                onClick = {
                    val jadwalDokter = JadwalDokter(
                        id = idJadwal.value,
                        tanggal = selectedDate.value.toString(),
                        hari = hari.value,
                        jam = jam.value,
                        status = status.value
                    )
                    createJadwalVM.createJadwal(idDok.value.toString(), jadwalDokter)
                    Toast.makeText(context, "Berhasil menambahkan data", Toast.LENGTH_SHORT).show()
                },
                text = "SIMPAN"
            )
        }
    }
}