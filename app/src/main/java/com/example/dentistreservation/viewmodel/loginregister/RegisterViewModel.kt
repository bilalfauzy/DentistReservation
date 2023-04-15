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
import java.util.UUID

class RegisterViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    val idUser = mutableStateOf(
        UUID.randomUUID().toString()
    )
    val nama = mutableStateOf("")
    val umur = mutableStateOf("")
    val gender = mutableStateOf<String?>(null)
    val noWa = mutableStateOf("")
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

    fun onNomorChange(noWa: String){
        this.noWa.value = noWa
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
        val idUser = idUser.value
        val nama = nama.value
        val umur = umur.value.toIntOrNull()
        val gender = gender.value
        val noWa = noWa.value
        val email = email.value
        val password = password.value

        if (isFormValid()){
            isLoading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        val user = Users(
                            idUser, nama, umur!!, gender, noWa, email, password
                        )
                        Firebase.firestore.collection("users")
                            .document(user.idUser!!)
                            .set(user)
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
        if (nama.value.isEmpty() || umur.value == null || gender.value!!.isEmpty() || email.value.isEmpty() ||
            password.value.isEmpty() || confirmPassword.value.isEmpty() || noWa.value.isEmpty()
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
        noWa.value = ""
        email.value = ""
        password.value = ""
        confirmPassword.value = ""
        error.value = ""
    }
}
