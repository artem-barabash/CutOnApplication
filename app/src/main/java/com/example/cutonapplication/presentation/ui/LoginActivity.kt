package com.example.cutonapplication.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cutonapplication.R
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.TEMP_USER_DATA
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.sharedPreferences

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = this.getSharedPreferences(TEMP_USER_DATA, MODE_PRIVATE)
    }
}