package com.example.cutonapplication.data.retrofit;

import com.example.cutonapplication.domain.entities.UserToken;
import com.example.cutonapplication.domain.entities.Version;
import com.example.cutonapplication.domain.entities.WorkResource;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequestServer {

    @GET("routes/")
    Call<WorkResource> getApiAddress(@Query("appName")String nameRoute, @Query("v") int numberRoute);

    @POST("/users/login/")
    @FormUrlEncoded
    Call<UserToken> sendUser(
            @Field("login") String login,
            @Field("password") String password,
            @Field("devman") String devman,
            @Field("devmod") String devmod,
            @Field("devavs") String devavs,
            @Field("devaid") String devaid
    );

    @GET("app/version/latest/")
    Call<Version> getServerVersion(@Query("v") int version);

    @DELETE("users/")
    Call<ResponseBody> deleteUserSession(@Query("token") String token);

}
