package com.example.dentistreservation.viewmodel.loginregister

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.dentistreservation.model.Users
import com.example.dentistreservation.routes.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    val nama = mutableStateOf("")
    val umur = mutableStateOf("")
    val gender = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val confirmPassword = mutableStateOf("")

    val error = mutableStateOf("")
    val isLoading = mutableStateOf(false)

    fun onNameChange(nama: String){
        this.nama.value = nama
    }
    fun onAgeChange(umur: String){
        this.umur.value = umur
    }
    fun onGenderChange(gender: String){
        this.gender.value = gender
    }
    fun onEmailChange(email: String){
        this.email.value = email
    }
    fun onPasswordChange(password: String){
        this.password.value = password
    }
    fun onConfirmPasswordChange(confirmPassword: String){
        this.confirmPassword.value = confirmPassword
    }

    fun onRegisterClick(navController: NavHostController){
        val nama = nama.value
        val umur = umur.value.toIntOrNull()
        val gender = gender.value
        val email = email.value
        val password = password.value

        if (isFormValid()){
            isLoading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        val user = Users(
                            nama, umur!!, gender, email, password
                        )
                        Firebase.firestore.collection("users")
                            .add(user)
                            .addOnSuccessListener {
                                isLoading.value = false
                                //navController.navigate("login")
                                navController.navigate(Screen.LoginScreen.route)
                                clearForm()
                            }
                            .addOnFailureListener {
                                isLoading.value = false
                                error.value = "Gagal menambahkan data: ${it.message}"
                            }

                    }else{
                        error.value = it.exception?.message?: "Error"
                    }
                }
        }

    }

    private fun isFormValid() : Boolean {
        if (nama.value.isEmpty() || umur.value == null || gender.value.isEmpty() || email.value.isEmpty() ||
            password.value.isEmpty() || confirmPassword.value.isEmpty()
        ){
            error.value = "Silahkan diisi.."
            return false
        }else if(password.value != confirmPassword.value){
            error.value = "Password tidak sama!"
            return false
        }
        return true
    }

    private fun clearForm(){
        nama.value = ""
        umur.value = ""
        gender.value = ""
        email.value = ""
        password.value = ""
        confirmPassword.value = ""
        error.value = ""
    }
}
