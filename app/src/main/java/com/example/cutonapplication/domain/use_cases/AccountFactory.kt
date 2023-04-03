package com.example.cutonapplication.domain.use_cases

import com.example.cutonapplication.domain.entities.Account

open class AccountFactory( val userAccount: Account) {

    init {
        USER_ACCOUNT = userAccount
    }

    companion object{
        lateinit var USER_ACCOUNT: Account
    }
}