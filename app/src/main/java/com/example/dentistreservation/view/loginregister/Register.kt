package com.example.dentistreservation.view.loginregister

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dentistreservation.R
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.view.customcomponent.*
import com.example.dentistreservation.viewmodel.loginregister.RegisterViewModel

@Composable
fun Register(
    navController: NavHostController,
    registerViewModel: RegisterViewModel
){
    val scrollState = rememberScrollState()
    var isError = false
    val context = LocalContext.current

    val listGender = listOf(
        "Laki - laki",
        "Perempuan",
    )

    Column(){

        TopAppBar(modifier = Modifier
            .background(MaterialTheme.colors.primary),
            title = {
                Text(text = "Register")
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            contentAlignment = Alignment.Center
        ){
            CustomCard(
                Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.6f)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Register"
                    )
                    //nama
                    CustomTextField(
                        value = registerViewModel.nama.value,
                        onValueChange = {
                            registerViewModel.onNameChange(it)
                        },
                        label = "Masukkan nama",
                        leadingIcon = {
                            Icon(painter = painterResource(
                                id = R.drawable.ic_email),
                                contentDescription = "Nama",
                                tint = MaterialTheme.colors.primary
                            )
                        },
                        isError = isError
                    )
                    //umur
                    CustomTextField(
                        value = registerViewModel.umur.value,
                        onValueChange = {
                            registerViewModel.onAgeChange(it)
                        },
                        label = "Masukkan umur",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        leadingIcon = {
                            Icon(painter = painterResource(
                                id = R.drawable.ic_email),
                                contentDescription = "Umur",
                                tint = MaterialTheme.colors.primary
                            )
                        },
                        isError = isError
                    )
                    //gender
                    CustomExposedDropdown(options = listGender, label = "Pilih gender", onOptionSelected = {
                        registerViewModel.onGenderChange(it)
                    }, selectedOption = registerViewModel.gender.value)

                    //nomor wa
                    CustomTextField(
                        value = registerViewModel.noWa.value,
                        onValueChange = {
                            registerViewModel.onNomorChange(it)
                        },
                        label = "Nomor telepon/Whatsapp",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        leadingIcon = {
                            Icon(painter = painterResource(
                                id = R.drawable.ic_email),
                                contentDescription = "Nomor",
                                tint = MaterialTheme.colors.primary
                            )
                        },
                        isError = isError
                    )

                    //email
                    CustomTextField(
                        value = registerViewModel.email.value,
                        onValueChange = {
                            registerViewModel.onEmailChange(it)
                        },
                        label = "Masukkan email",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        leadingIcon = {
                            Icon(painter = painterResource(
                                id = R.drawable.ic_email),
                                contentDescription = "Email",
                                tint = MaterialTheme.colors.primary
                            )
                        },
                        isError = isError
                    )

                    //password
                    CustomTextField(
                        value = registerViewModel.password.value,
                        onValueChange = {
                            registerViewModel.onPasswordChange(it)
                        },
                        label = "Masukkan password",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(painter = painterResource(
                                id = R.drawable.ic_lock),
                                contentDescription = "Password",
                                tint = MaterialTheme.colors.primary
                            )
                        },
                        isError = isError
                    )

                    //konfirmasi password
                    CustomTextField(
                        value = registerViewModel.confirmPassword.value,
                        onValueChange = {
                            registerViewModel.onConfirmPasswordChange(it)
                        },
                        label = "Konfirmasi password",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(painter = painterResource(
                                id = R.drawable.ic_lock),
                                contentDescription = "Password",
                                tint = MaterialTheme.colors.primary
                            )
                        },
                        isError = isError
                    )

                    //validasi
                    if (registerViewModel.error.value.isNotEmpty()){
                        Text(
                            text = registerViewModel.error.value,
                            color = MaterialTheme.colors.error,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    CustomSpacer()
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        onClick = {
                            registerViewModel.onRegisterClick(navController)
                        }) {
                        if (registerViewModel.isLoading.value){
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(4.dp),
                                color = MaterialTheme.colors.onPrimary
                            )
                        }else{
                            Text(text = "REGISTER")
                        }
                    }
                    CustomSpacer()
                    Text(text = "Sudah mempunyai akun?")
                    CustomSpacer()
                    MyButton(
                        onClick = {
                            navController.navigate(Screen.LoginScreen.route)
                        },
                        text = "LOGIN"
                    )
                }
            }
        }


    }
}



