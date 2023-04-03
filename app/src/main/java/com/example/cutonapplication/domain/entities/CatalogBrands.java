package com.example.cutonapplication.domain.entities;

import android.content.ClipData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CatalogBrands {
    @Expose
    @SerializedName("brands")
    Map<String, Brand> map;

    public Map<String, Brand> getMap() {
        return map;
    }

    public void setMap(Map<String, Brand> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "CatalogBrands{" +
                "map=" + map +
                '}';
    }

    public static class Brand{
        @Expose
        @SerializedName("brandId")
        private Integer brandId;
        @Expose
        @SerializedName("brandName")
        private String brandName;
        @Expose
        @SerializedName("brandImage")
        private String brandImage;

        public Integer getBrandId() {
            return brandId;
        }

        public void setBrandId(Integer brandId) {
            this.brandId = brandId;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getBrandImage() {
            return brandImage;
        }

        public void setBrandImage(String brandImage) {
            this.brandImage = brandImage;
        }

        @Override
        public String toString() {
            return "Brand{" +
                    "brandId=" + brandId +
                    ", brandName='" + brandName + '\'' +
                    ", brandImage='" + brandImage + '\'' +
                    '}';
        }
    }
}
