package com.example.dentistreservation.view.reservasi

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.view.CustomExposedDropdown
import com.example.dentistreservation.view.MyAppBar
import com.example.dentistreservation.viewmodel.reservasi.MemilihDokterVM
import com.example.dentistreservation.viewmodel.reservasi.MemilihTanggalVM
import java.time.LocalDate
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MemilihTanggal(
    navController: NavHostController,
    memilihTanggalVM: MemilihTanggalVM,
    memilihDokterVM: MemilihDokterVM
){

    val namaDok = remember {
        mutableStateOf<String?>(null)
    }

    val selectedDate = remember {
        mutableStateOf(LocalDate.now())
    }

    val jam = remember {
        mutableStateOf<String?>(null)
    }

    val listJam = listOf(
        "08.00",
        "10.00",
        "13.00",
        "15.00"
    )

    val keluhan = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column() {
        MyAppBar(
            title = "Memilih tanggal",
            navigationIcon = Icons.Filled.ArrowBack,
            onNavigationClick = {
                navController.navigate(Screen.HomeScreen.route)
            }
        )

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(40.dp)
        ) {

            Text(text = "Pilih tanggal")
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

            CustomExposedDropdown(options = listJam, label = "Pilih waktu", onOptionSelected = {
                jam.value = it
            }, selectedOption = jam.value)

            if (selectedDate.value.toString().isNotEmpty() && jam.value != null){
                
                val dokterList by memilihTanggalVM.dokterByDate(selectedDate.value.toString(), jam.value!!).collectAsState(
                    initial = emptyList()
                )

                val listNama = dokterList.map {
                    it.nama
                }
                //pilih daftar dokter
                if (listNama.isNotEmpty()){
                    CustomExposedDropdown(options = listNama as List<String>, label = "Pilih dokter", onOptionSelected = {
                        namaDok.value = it
                    }, selectedOption = namaDok.value)
                }else{
                    Text(text = "Tidak ada dokter tersedia")
                }
            }


            //isi keluhan
            OutlinedTextField(
                value = keluhan.value,
                onValueChange = {
                    keluhan.value = it
                },
                label = {
                    Text("Isi keluhan")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    navController.navigate(Screen.MelakukanPembayaranScreen.route+
                            "/${namaDok.value}/${selectedDate.value}/${selectedDate.value.dayOfWeek}/${jam.value}/${keluhan.value}")
                }
            ) {
                Text(text = "Pilih")
            }
        }
    }
}