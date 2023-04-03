package com.example.cutonapplication.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cutonapplication.domain.entities.Account
import com.example.cutonapplication.presentation.viewmodel.HomeViewModel

class HomeViewModelFactory(private val account: Account) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(account) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}