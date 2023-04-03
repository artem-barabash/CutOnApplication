package com.example.cutonapplication.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {
    @SerializedName("userId")
    @Expose
    public int userId;
    @SerializedName("firstname")
    @Expose
    public String firstName;
    @SerializedName("lastname")
    @Expose
    public String lastName;
    @SerializedName("pmFirstname")
    @Expose
    public String pmFirstName;
    @SerializedName("pmLastname")
    @Expose
    public String pmLastName;
    @SerializedName("pmTelephone")
    @Expose
    public String pmTelephone;
    @SerializedName("tsFirstname")
    @Expose
    public String tsFirstName;
    @SerializedName("tsLastname")
    @Expose
    public String tsLastName;
    @SerializedName("tsTelephone")
    @Expose
    public String tsTelephone;
    @SerializedName("balance")
    @Expose
    public int balance;
    @SerializedName("bonusToday")
    @Expose
    public int bonusToday;
    @SerializedName("bonusTotal")
    @Expose
    public int bonusTotal;
    @SerializedName("bonusTitle")
    @Expose
    public String bonusTitle;


    public Account(int userId, String firstName, String lastName, String pmFirstName, String pmLastName, String pmTelephone, String tsFirstName, String tsLastName, String tsTelephone, int balance, int bonusToday, int bonusTotal, String bonusTitle) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pmFirstName = pmFirstName;
        this.pmLastName = pmLastName;
        this.pmTelephone = pmTelephone;
        this.tsFirstName = tsFirstName;
        this.tsLastName = tsLastName;
        this.tsTelephone = tsTelephone;
        this.balance = balance;
        this.bonusToday = bonusToday;
        this.bonusTotal = bonusTotal;
        this.bonusTitle = bonusTitle;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPmFirstName() {
        return pmFirstName;
    }

    public void setPmFirstName(String pmFirstName) {
        this.pmFirstName = pmFirstName;
    }

    public String getPmLastName() {
        return pmLastName;
    }

    public void setPmLastName(String pmLastName) {
        this.pmLastName = pmLastName;
    }

    public String getPmTelephone() {
        return pmTelephone;
    }

    public void setPmTelephone(String pmTelephone) {
        this.pmTelephone = pmTelephone;
    }

    public String getTsFirstName() {
        return tsFirstName;
    }

    public void setTsFirstName(String tsFirstName) {
        this.tsFirstName = tsFirstName;
    }

    public String getTsLastName() {
        return tsLastName;
    }

    public void setTsLastName(String tsLastName) {
        this.tsLastName = tsLastName;
    }

    public String getTsTelephone() {
        return tsTelephone;
    }

    public void setTsTelephone(String tsTelephone) {
        this.tsTelephone = tsTelephone;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBonusToday() {
        return bonusToday;
    }

    public void setBonusToday(int bonusToday) {
        this.bonusToday = bonusToday;
    }

    public int getBonusTotal() {
        return bonusTotal;
    }

    public void setBonusTotal(int bonusTotal) {
        this.bonusTotal = bonusTotal;
    }

    public String getBonusTitle() {
        return bonusTitle;
    }

    public void setBonusTitle(String bonusTitle) {
        this.bonusTitle = bonusTitle;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pmFirstName='" + pmFirstName + '\'' +
                ", pmLastName='" + pmLastName + '\'' +
                ", pmTelephone='" + pmTelephone + '\'' +
                ", tsFirstName='" + tsFirstName + '\'' +
                ", tsLastName='" + tsLastName + '\'' +
                ", tsTelephone='" + tsTelephone + '\'' +
                ", balance=" + balance +
                ", bonusToday=" + bonusToday +
                ", bonusTotal=" + bonusTotal +
                ", bonusTitle='" + bonusTitle + '\'' +
                '}';
    }
}
