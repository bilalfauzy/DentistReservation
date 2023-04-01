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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.ui.theme.baseColor
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
    val loginResult = loginViewModel.loginState.value
    val context = LocalContext.current

    TopAppBar(modifier = Modifier
        .background(baseColor)
        .fillMaxWidth()
        .fillMaxHeight(0.1f)) {
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Login"
        )

        OutlinedTextField(
            value = email.value,
            label = {
                Text("Masukkan email..")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            onValueChange = {
                email.value = it
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password.value,
            label = {
                Text("Masukkan password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                password.value = it
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                loginViewModel.onLoginClick(
                    navController,
                    email.value,
                    password.value
                )
                if (loginResult != null){
                    when(loginResult){
                        is LoginState.Success ->{
                            Toast.makeText(context, "Login berhasil", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(context, "Login gagal", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }) {
            Text("Login")
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Belum mempunyai akun?")
            Button(
                onClick = {
                    navController.navigate(Screen.RegisterScreen.route)
                }
            ) {
                Text("Register")
            }
        }
    }
}


