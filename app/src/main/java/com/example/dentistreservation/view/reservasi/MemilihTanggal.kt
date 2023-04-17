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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dentistreservation.R
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.ui.theme.backColor
import com.example.dentistreservation.view.customcomponent.*
import com.example.dentistreservation.viewmodel.reservasi.MemilihTanggalVM
import java.time.LocalDate
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MemilihTanggal(
    navController: NavHostController,
    memilihTanggalVM: MemilihTanggalVM
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
    var isError = false

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
                .background(backColor)
                .fillMaxSize()
                .padding(20.dp)
        ) {

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
                            }
                        ) {
                            Icon(imageVector = Icons.Outlined.DateRange, contentDescription = "Pilih tanggal")
                        }
                    }
                )
            }

            CustomSpacer()
            CustomExposedDropdown(options = listJam, label = "Pilih waktu", onOptionSelected = {
                jam.value = it
            }, selectedOption = jam.value)

            if (selectedDate.value.toString().isNotEmpty() && jam.value != null){
                
                val dokterList by memilihTanggalVM.dokterByDate(selectedDate.value.toString(), jam.value!!).collectAsState(
                    initial = emptyList()
                )

                val listNama = dokterList.map {
                    it.nama.toString()
                }
                //pilih daftar dokter
                if (listNama.isNotEmpty()){
                    CustomSpacer()
                    CustomExposedDropdown(options = listNama, label = "Pilih dokter", onOptionSelected = {
                        namaDok.value = it
                    }, selectedOption = namaDok.value)
                }else{
                    CustomSpacer()
                    Text(text = "Tidak ada dokter tersedia")
                }
            }

            CustomSpacer()
            //isi keluhan
            CustomTextField(
                value = keluhan.value,
                onValueChange = {
                    keluhan.value = it
                    isError = it.isEmpty()
                },
                label = "Keluhan",
                leadingIcon = {
                    Icon(painter = painterResource(
                        id = R.drawable.ic_email),
                        contentDescription = "Email",
                        tint = MaterialTheme.colors.primary
                    )
                },
                isError = isError
            )

            CustomSpacer()

            MyButton(
                onClick = {
                    navController.navigate(Screen.MelakukanPembayaranScreen.route+
                            "/${namaDok.value}/${selectedDate.value}/${selectedDate.value.dayOfWeek}/${jam.value}/${keluhan.value}")
                },
                text = "OK"
            )

        }
    }
}