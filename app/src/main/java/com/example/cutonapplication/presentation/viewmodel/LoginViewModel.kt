package com.example.cutonapplication.presentation.viewmodel

import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cutonapplication.data.AppConstants
import com.example.cutonapplication.data.AppConstants.BASE_URL
import com.example.cutonapplication.data.retrofit.ItemsRequest
import com.example.cutonapplication.data.retrofit.RouteRequest
import com.example.cutonapplication.domain.entities.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(): ViewModel() {


    init {
    }


    fun getApiAddress(): LiveData<WorkResource?>? {
        val routeRequest =
            RouteRequest(BASE_URL)

        val data = MutableLiveData<WorkResource?>()
        routeRequest.apiRequest.getApiAddress(AppConstants.APP_NAME, AppConstants.VERSION)
            .enqueue(object : Callback<WorkResource?> {
                override fun onResponse(
                    call: Call<WorkResource?>,
                    response: Response<WorkResource?>
                ) {
                    data.postValue(response.body())
                    println("address=${response.body()}")
                }

                override fun onFailure(call: Call<WorkResource?>, t: Throwable) {
                    data.postValue(null)
                }
            })
        return data
    }

    fun getVersion(number: Int, address: String):LiveData<Version?>?{
        val routeRequest =
            RouteRequest(address)
        val data = MutableLiveData<Version?>()

        routeRequest.apiRequest.getServerVersion(number)
            .enqueue(object : Callback<Version?>{
                override fun onResponse(call: Call<Version?>, response: Response<Version?>) {
                    data.postValue(response.body())
                    println("version=${response.body()!!.number}")
                }

                override fun onFailure(call: Call<Version?>, t: Throwable) {
                    data.postValue(null)
                }
            })



        return data
    }

    fun getUserToken(link:String, phone: String, password: String): LiveData<UserToken?>? {
        val liveData = MutableLiveData<UserToken?>()
        val routeRequest = RouteRequest(link)

        val user = User(phone, password, Build.MANUFACTURER, Build.MODEL, Build.VERSION.RELEASE, Build.ID)
        println(user)

        routeRequest.apiRequest.sendUser(user.login, user.password,
            user.devman, user.devmod, user.devavs, user.devaid)
            .enqueue(object : Callback<UserToken?>{
                override fun onResponse(call: Call<UserToken?>, response: Response<UserToken?>) {
                    liveData.postValue(response.body())
                }

                override fun onFailure(call: Call<UserToken?>, t: Throwable) {
                    liveData.postValue(null)
                }
            })


        return liveData
    }



    fun getUserAccount(address: String, token: String){
        val itemsRequest = ItemsRequest(address)

        itemsRequest.apiItems.getAccount(token).enqueue(object: Callback<Account?>{
            override fun onResponse(call: Call<Account?>, response: Response<Account?>) {
                sharedPreferences.edit().clear().apply()
               saveDataSharedPreferences(response.body())
            }

            override fun onFailure(call: Call<Account?>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    private fun saveDataSharedPreferences(account: Account?) {
        val editor = sharedPreferences.edit()

        editor.putInt(USER_ID, account!!.userId)
        editor.putString(FIRST_NAME, account.firstName)
        editor.putString(LAST_NAME, account.lastName)
        editor.putString(PM_FIRST_NAME, account.pmFirstName)
        editor.putString(PM_LAST_NAME, account.pmLastName)
        editor.putString(PM_TELEPHONE, account.pmTelephone)
        editor.putString(TS_FIRST_NAME, account.tsFirstName)
        editor.putString(TS_LAST_NAME, account.tsLastName)
        editor.putString(TS_TELEPHONE, account.tsTelephone)
        editor.putInt(BALANCE, account.balance)
        editor.putInt(BONUS_TODAY, account.bonusToday)
        editor.putInt(BONUS_TOTAL, account.bonusTotal)
        editor.putString(BONUS_TITLE, account.bonusTitle)

        editor.apply()
    }


    companion object{
       lateinit var sharedPreferences: SharedPreferences

        const val TEMP_USER_DATA : String = "MySharedPref"

        const val USER_ID: String = "user_id"
        const val FIRST_NAME : String = "first_name"
        const val LAST_NAME : String = "last_name"
        const val PM_FIRST_NAME : String = "pm_first_name"
        const val PM_LAST_NAME : String = "pm_last_name"
        const val PM_TELEPHONE : String = "pm_telephone"
        const val TS_FIRST_NAME : String = "ts_first_name"
        const val TS_LAST_NAME : String = "ts_last_name"
        const val TS_TELEPHONE : String = "ts_telephone"
        const val BALANCE : String = "balance"
        const val BONUS_TODAY: String = "bonusToday"
        const val BONUS_TOTAL: String = "bonusTotal"
        const val BONUS_TITLE: String = "bonusTitle"
    }
}