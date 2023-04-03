package com.example.cutonapplication.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Version {
    @SerializedName("answer")
    @Expose
    int number;

    public Version(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
