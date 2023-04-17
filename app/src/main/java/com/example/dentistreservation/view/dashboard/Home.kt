package com.example.dentistreservation.view.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dentistreservation.R
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.ui.theme.backColor
import com.example.dentistreservation.view.customcomponent.CustomCard
import com.example.dentistreservation.view.customcomponent.CustomSpacer
import com.example.dentistreservation.view.customcomponent.MyAppBar
import com.example.dentistreservation.view.customcomponent.MyButton
import com.example.dentistreservation.viewmodel.UsersViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Home(
    navController: NavHostController,
    usersViewModel: UsersViewModel
){
    val emailLogin = FirebaseAuth.getInstance().currentUser?.email
    usersViewModel.getUserLogin(emailLogin!!)
    val userLogin by usersViewModel.userLogin.collectAsState(emptyList())

    val namaList = userLogin.map {
        it.nama.toString()
    }
    val namaUser = namaList.joinToString()
    Column() {
        MyAppBar(
            title = "Home",
            navigationIcon = Icons.Filled.ArrowBack,
            onNavigationClick = {
            }
        )
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(backColor)
                .padding(20.dp)
        ){

            //Selamat datang user
            CustomCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ){
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Column() {
                        Text(text = "Selamat datang..")
                        Text(text = namaUser)
                    }
                }
                Image(painter = painterResource(
                    id = R.drawable.ic_email
                ), contentDescription = "Profile")
            }

            CustomSpacer()
            CustomSpacer()
            //Buat reservasi
            CustomCard(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clickable {
                    navController.navigate(Screen.MemilihTanggalScreen.route)
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "Arrow",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(60.dp),
                        tint = Color.LightGray
                    )
                    Text(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        text = "BUAT RESERVASI",
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "Arrow",
                        modifier = Modifier
                            .offset(x = 110.dp)
                            .size(30.dp),
                        tint = Color.LightGray
                    )
                }
            }

            CustomSpacer()
            CustomSpacer()
            //History reservasi
            CustomCard(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clickable {
                    navController.navigate(Screen.ListReservasiScreen.route)
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "History",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(60.dp),
                        tint = Color.LightGray
                    )
                    Text(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        text = "HISTORY",
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "Arrow",
                        modifier = Modifier
                            .offset(x = 110.dp)
                            .size(30.dp),
                        tint = Color.LightGray
                    )
                }
            }

            CustomSpacer()
            CustomSpacer()
            CustomCard(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clickable {
                    navController.navigate(Screen.ProfileScreen.route)
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "Arrow",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(60.dp),
                        tint = Color.LightGray
                    )
                    Text(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        text = "PROFILE",
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "Arrow",
                        modifier = Modifier
                            .offset(x = 110.dp)
                            .size(30.dp),
                        tint = Color.LightGray
                    )
                }
            }

        }
    }
}