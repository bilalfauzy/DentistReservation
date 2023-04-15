package com.example.dentistreservation.view.loginregister

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dentistreservation.R
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.ui.theme.baseColor
import com.example.dentistreservation.view.customcomponent.CustomCard
import com.example.dentistreservation.view.customcomponent.CustomSpacer
import com.example.dentistreservation.view.customcomponent.CustomTextField
import com.example.dentistreservation.view.customcomponent.MyButton
import com.example.dentistreservation.viewmodel.loginregister.LoginState
import com.example.dentistreservation.viewmodel.loginregister.LoginViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun Login(
    navController: NavHostController,
    loginViewModel: LoginViewModel
){
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    var isError = false
    val loginResult = loginViewModel.loginState.value
    val context = LocalContext.current

    Column {
        TopAppBar(modifier = Modifier
            .background(MaterialTheme.colors.primary),
            title = {
                Text(text = "Login")
            }
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CustomCard(
                Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.6f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Login"
                    )

                    CustomTextField(
                        value = email.value,
                        onValueChange = {
                            email.value = it
                            isError = it.isEmpty()

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

                    CustomTextField(
                        value = password.value,
                        onValueChange = {
                            password.value = it
                            isError = it.isEmpty()

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

                    CustomSpacer()
                    MyButton(
                        onClick = {
                              if (email.value.isNotEmpty() && password.value.isNotEmpty()){
                                  loginViewModel.onLoginClick(
                                      navController,
                                      email.value,
                                      password.value
                                  )
                              }else{
                                  Toast.makeText(context, "Form tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                              }
                        },
                        text = "Login"
                    )
                    CustomSpacer()
                    Text(text = "Belum mempunyai akun?")
                    CustomSpacer()
                    MyButton(
                        onClick = {
                              navController.navigate(Screen.RegisterScreen.route)
                        },
                        text = "Register"
                    )

                }
            }
        }
    }
}


