package com.example.deepgandhi.stockappfrags.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GraphDetails {
    @SerializedName("priceData")
    @Expose
    private float[][] arr;

    @SerializedName("assetName")
    @Expose
    private String name;

    public GraphDetails(float[][] arr, String name) {
        this.arr = arr;
        this.name = name;
    }

    public float[][] getArr() {
        return arr;
    }

    public void setArr(float[][] arr) {
        this.arr = arr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
