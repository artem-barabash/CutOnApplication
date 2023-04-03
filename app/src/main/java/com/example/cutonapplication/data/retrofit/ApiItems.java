package com.example.cutonapplication.data.retrofit;

import com.example.cutonapplication.domain.entities.Account;
import com.example.cutonapplication.domain.entities.CatalogBrands;
import com.example.cutonapplication.domain.entities.Menu;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiItems {

    @GET("/users/")
    Call<Account> getAccount(@Query("token") String token);

    @GET("home/menu/items/")
    Call<Menu> getMenuItems(@Query("token") String token);

    @GET("catalog/brands/")
    Call<CatalogBrands> getCatalogBrands(@Query("token") String token);
}
