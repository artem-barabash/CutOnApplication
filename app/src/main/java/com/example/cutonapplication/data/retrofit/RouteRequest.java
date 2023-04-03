package com.example.cutonapplication.data.retrofit;

import static com.example.cutonapplication.data.AppConstants.APP_NAME;
import static com.example.cutonapplication.data.AppConstants.BASE_URL;
import static com.example.cutonapplication.data.AppConstants.VERSION;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cutonapplication.domain.entities.WorkResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RouteRequest {

    public ApiRequestServer apiRequest;

    public RouteRequest(String address){
        apiRequest = RetrofitBuilder.getInstance(address).create(ApiRequestServer.class);
    }

}
