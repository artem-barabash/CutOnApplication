package com.example.cutonapplication.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import com.example.cutonapplication.R
import com.example.cutonapplication.databinding.ActivityExitBinding
import com.example.cutonapplication.domain.use_cases.AccountFactory.Companion.USER_ACCOUNT
import com.example.cutonapplication.presentation.viewmodel.HomeViewModel
import com.example.cutonapplication.presentation.viewmodel.factory.HomeViewModelFactory

class ExitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExitBinding

    private lateinit var exitAddress: String
    private lateinit var exitToken: String

    private val sharedViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(USER_ACCOUNT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityExitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        super.onCreate(savedInstanceState)


        exitAddress = intent.extras?.getString(SELECTED_ADDRESS)?:""
        exitToken = intent.extras?.getString(SELECTED_TOKEN)?:""

        this.onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }

        })

        binding.buttonNo.setOnClickListener {
            backToStandartFragment()
        }

        binding.buttonYes.setOnClickListener {
            backFromApplication()
        }


    }

    private fun backFromApplication() {
        this.finishAffinity()
        sharedViewModel.deleteSession(exitAddress, exitToken)
    }

    private fun backToStandartFragment() {
        this.finish()
    }

    companion object{
        const val SELECTED_TOKEN = "selected_token"
        const val SELECTED_ADDRESS = "selected_address"
    }
}