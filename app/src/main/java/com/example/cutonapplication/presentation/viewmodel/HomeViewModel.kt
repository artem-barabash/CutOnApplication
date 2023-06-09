package com.example.cutonapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cutonapplication.data.retrofit.ItemsRequest
import com.example.cutonapplication.data.retrofit.RouteRequest
import com.example.cutonapplication.domain.entities.Account
import com.example.cutonapplication.domain.entities.Menu
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(val account: Account): ViewModel() {

    fun getListMenuItems(link: String, token: String): MutableLiveData<Menu?> {
        val data = MutableLiveData<Menu?>()

        val itemsRequest = ItemsRequest(link)

        itemsRequest.apiItems.getMenuItems(token)
            .enqueue(object : Callback<Menu>{
                override fun onResponse(
                    call: Call<Menu>,
                    response: Response<Menu>
                ) {
                    if(response.isSuccessful){
                        data.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<Menu>, t: Throwable) {
                    data.postValue(null)
                }
            })

        return  data
    }

    fun deleteSession(exitAddress: String, exitToken: String) {
        val routeRequest = RouteRequest(exitAddress)

        routeRequest.apiRequest.deleteUserSession(exitToken)
            .enqueue(object : Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if(response.isSuccessful){
                        println("deleted")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    println(t.message)
                }
            })
    }
}