package com.example.cutonapplication.domain.entities;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Menu {
    @SerializedName("items")
    @Expose
    private Menu.MenuItem menuItem;

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuItem=" + menuItem +
                '}';
    }

    public static class MenuItem {
        @SerializedName("itemId")
        @Expose
        private Integer idItem;
        @SerializedName("itemName")
        @Expose
        private String nameItem;
        @SerializedName("itemImage")
        @Expose
        private String photo;


        public Integer getIdItem() {
            return idItem;
        }

        public void setIdItem(Integer idItem) {
            this.idItem = idItem;
        }

        public String getNameItem() {
            return nameItem;
        }

        public void setNameItem(String nameItem) {
            this.nameItem = nameItem;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        @Override
        public String toString() {
            return "MenuItem{" +
                    "idItem=" + idItem +
                    ", nameItem='" + nameItem + '\'' +
                    ", photo='" + photo + '\'' +
                    '}';
        }
    }

}
