package com.example.dentistreservation.view.reservasi

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.example.dentistreservation.model.DokterGigi
import com.example.dentistreservation.model.JadwalDokter
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.view.MyAppBar
import com.example.dentistreservation.viewmodel.reservasi.MemilihDokterVM
import com.example.dentistreservation.viewmodel.reservasi.MemilihTanggalVM
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MemilihTanggal(
    navController: NavHostController,
    memilihTanggalVM: MemilihTanggalVM,
    memilihDokterVM: MemilihDokterVM,
    idDok: String,
    namaDok: String,
    genderDok: String,
    spesialis: String,
    umurDok: String
){
    val selectedDate = remember {
        mutableStateOf(LocalDate.now())
    }

    val selectedDokter = remember {
        mutableStateOf("")
    }

    var mExpanded = remember {
        mutableStateOf(false)
    }

    val icon  = if (mExpanded.value){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }


    val keluhan = remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val dokter = remember {
        DokterGigi(
            id = idDok,
            nama = namaDok,
            gender = genderDok,
            spesialis = spesialis,
            umur = umurDok
        )
    }
    val jadwal = remember {
        JadwalDokter()
    }

    Column() {
        MyAppBar(
            title = "Memilih tanggal",
            navigationIcon = Icons.Filled.ArrowBack,
            onNavigationClick = {

            }
        )

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(40.dp)
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "ID : ${dokter.id}")
                    Text(text = "Nama : ${dokter.nama}")
                    Text(text = "Gender : ${dokter.gender}")
                    Text(text = "Spesialis : ${dokter.spesialis}")
                    Text(text = "Umur : ${dokter.umur}")
                }
            }

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

            if (selectedDate.value.toString().isNotEmpty()){
                
                val dokterList by memilihTanggalVM.dokterByDate(selectedDate.value.toString()).collectAsState(
                    initial = emptyList()
                )
                //pilih daftar dokter
                OutlinedTextField(
                    value = selectedDokter.value,
                    onValueChange = { selectedDokter.value = it },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = {Text("Pilih dokter")},
                    trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { mExpanded.value = true })
                    }
                )

                DropdownMenu(
                    expanded = mExpanded.value,
                    onDismissRequest = { mExpanded.value = false }
                ) {
                    dokterList.forEach {
                        DropdownMenuItem(onClick = {
                            selectedDokter.value = it.nama.toString()
                            mExpanded.value = false
                        }){
                            Text(text = "${it.nama.toString()}")
                        }
                    }
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
                    navController.navigate(Screen.MelakukanPembayaranScreen.route)
                }
            ) {
                Text(text = "Pilih")
            }
        }
    }
}

@Composable
fun ListDokter(
    dokterList : List<DokterGigi>,
    dokter: DokterGigi,
    onItemClick: (DokterGigi) -> Unit
){
    LazyColumn(){
        items(dokterList){ dokter ->
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable { onItemClick(dokter) },
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Nama : ${dokter.nama}")
                    Text(text = "Spesialis : ${dokter.spesialis}")
                }
            }
        }
    }
}