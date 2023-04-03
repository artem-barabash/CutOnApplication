package com.example.cutonapplication.data.retrofit;

public class ItemsRequest {
    public ApiItems apiItems;

    public ItemsRequest(String address){
        apiItems = RetrofitBuilder.getInstance(address).create(ApiItems.class);
    }
}
