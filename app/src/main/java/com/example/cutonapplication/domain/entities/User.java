package com.example.cutonapplication.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("devman")
    @Expose
    public String devman;
    @SerializedName("devmod")
    @Expose
    public String devmod;
    @SerializedName("devavs")
    @Expose
    public String devavs;
    @SerializedName("devaid")
    @Expose
    public String devaid;

    public User(String login, String password, String devman, String devmod, String devavs, String devaid) {
        this.login = login;
        this.password = password;
        this.devman = devman;
        this.devmod = devmod;
        this.devavs = devavs;
        this.devaid = devaid;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevman() {
        return devman;
    }

    public void setDevman(String devman) {
        this.devman = devman;
    }

    public String getDevmod() {
        return devmod;
    }

    public void setDevmod(String devmod) {
        this.devmod = devmod;
    }

    public String getDevavs() {
        return devavs;
    }

    public void setDevavs(String devavs) {
        this.devavs = devavs;
    }

    public String getDevaid() {
        return devaid;
    }

    public void setDevaid(String devaid) {
        this.devaid = devaid;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", devman='" + devman + '\'' +
                ", devmod='" + devmod + '\'' +
                ", devavs='" + devavs + '\'' +
                ", devaid='" + devaid + '\'' +
                '}';
    }
}
