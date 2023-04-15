package com.example.dentistreservation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.dentistreservation.routes.Navigation
import com.example.dentistreservation.ui.theme.DentistReservationTheme
import com.example.dentistreservation.view.loginregister.Register
import com.example.dentistreservation.viewmodel.loginregister.RegisterViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DentistReservationTheme {
                window.statusBarColor = MaterialTheme.colors.primary.toArgb()
                Navigation()
            }
        }
    }
}
