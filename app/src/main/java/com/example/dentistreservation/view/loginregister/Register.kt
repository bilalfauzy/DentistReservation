package com.example.dentistreservation.view.loginregister

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.dentistreservation.routes.Screen
import com.example.dentistreservation.viewmodel.loginregister.RegisterViewModel

@Composable
fun Register(
    navController: NavHostController,
    registerViewModel: RegisterViewModel
){
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Register"
        )

        OutlinedTextField(
            value = registerViewModel.nama.value,
            label = {
                    Text("Masukkan nama..")
            },
            onValueChange = {
                registerViewModel.onNameChange(it)
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = registerViewModel.umur.value,
            label = {
                Text("Masukkan umur..")
            },
            onValueChange = {
                registerViewModel.onAgeChange(it)
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = registerViewModel.gender.value,
            label = {
                Text("Masukkan gender..")
            },
            onValueChange = {
                registerViewModel.onGenderChange(it)
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = registerViewModel.email.value,
            label = {
                Text("Masukkan email..")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            onValueChange = {
                registerViewModel.onEmailChange(it)
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = registerViewModel.password.value,
            label = {
                Text("Masukkan password..")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            onValueChange = {
                registerViewModel.onPasswordChange(it)
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = registerViewModel.confirmPassword.value,
            label = {
                Text("Konfirmasi password..")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            onValueChange = {
                registerViewModel.onConfirmPasswordChange(it)
            },
            modifier = Modifier.fillMaxWidth()
        )

        if (registerViewModel.error.value.isNotEmpty()){
            Text(
                text = registerViewModel.error.value,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(16.dp)
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
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
                Text(text = "Register")
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Sudah mempunyai akun?")
            Button(
                onClick = {
                    navController.navigate(Screen.LoginScreen.route)
                }
            ) {
                Text("Login")
            }
        }
    }
}



