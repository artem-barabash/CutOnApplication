package com.example.cutonapplication.presentation.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cutonapplication.R
import com.example.cutonapplication.databinding.ActivityHomeBinding
import com.example.cutonapplication.domain.entities.Account
import com.example.cutonapplication.domain.use_cases.AccountFactory
import com.example.cutonapplication.presentation.ui.fragments_home.StandardFragment
import com.example.cutonapplication.presentation.ui.fragments_home.CatalogFragment
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.BALANCE
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.BONUS_TITLE
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.BONUS_TODAY
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.BONUS_TOTAL
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.FIRST_NAME
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.LAST_NAME
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.PM_FIRST_NAME
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.PM_LAST_NAME
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.PM_TELEPHONE
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.TEMP_USER_DATA
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.TS_FIRST_NAME
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.TS_LAST_NAME
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.TS_TELEPHONE
import com.example.cutonapplication.presentation.viewmodel.LoginViewModel.Companion.USER_ID

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    lateinit var preferences: SharedPreferences

    private lateinit var userAddress:String
    private lateinit var userToken:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAddress = intent!!.getStringExtra(CURRENT_ADDRESS).toString()
        userToken = intent!!.getStringExtra(CURRENT_TOKEN).toString()

        println(userAddress)
        println(userToken)

        preferences = this.getSharedPreferences(TEMP_USER_DATA, MODE_PRIVATE)
        getData()

        replaceFragment(StandardFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.itemHome -> replaceFragment(StandardFragment())
                R.id.itemBrands -> replaceFragment(CatalogFragment())
            }

            return@setOnItemSelectedListener true
        }
    }

    private fun getData() {
        preferences = this.getSharedPreferences(TEMP_USER_DATA, MODE_PRIVATE)

        val account = Account(
            preferences.getInt(USER_ID, 0),
            preferences.getString(FIRST_NAME, "").toString(),
            preferences.getString(LAST_NAME, "").toString(),
            preferences.getString(PM_FIRST_NAME, "").toString(),
            preferences.getString(PM_LAST_NAME, "").toString(),
            preferences.getString(PM_TELEPHONE,"").toString(),
            preferences.getString(TS_FIRST_NAME, "").toString(),
            preferences.getString(TS_LAST_NAME, "").toString(),
            preferences.getString(TS_TELEPHONE, "").toString(),
            preferences.getInt(BALANCE, 0),
            preferences.getInt(BONUS_TODAY, 0),
            preferences.getInt(BONUS_TOTAL, 0),
            preferences.getString(BONUS_TITLE, "").toString()
        )

        println(account)

        AccountFactory(account)
    }

    private fun replaceFragment(selected: Fragment){
        val bundle = Bundle()
        bundle.putString(CURRENT_ADDRESS, userAddress)
        bundle.putString(CURRENT_TOKEN, userToken)


        selected.arguments = bundle

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_layout, selected)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object{
        const val CURRENT_ADDRESS = "current_address"
        const val CURRENT_TOKEN = "current_token"
    }
}