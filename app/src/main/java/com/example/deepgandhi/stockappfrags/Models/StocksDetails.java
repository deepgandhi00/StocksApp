package com.example.deepgandhi.stockappfrags.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StocksDetails {

    @SerializedName("contractName")
    @Expose
    private String name;

    @SerializedName("symbol")
    @Expose
    private String symbol;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("lastPrice")
    @Expose
    private float lastprice;

    @SerializedName("pricingDate")
    @Expose
    private String pricingDate;

    @SerializedName("highPrice")
    @Expose
    private float highPrice;

    @SerializedName("lowPrice")
    @Expose
    private float lowPrice;

    private int id;


    public StocksDetails(String name, String symbol, String currency, float lastprice, String pricingDate,
                         float highPrice, float lowPrice) {
        this.name = name;
        this.symbol = symbol;
        this.currency = currency;
        this.lastprice = lastprice;
        this.pricingDate = pricingDate;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getLastprice() {
        return lastprice;
    }

    public void setLastprice(float lastprice) {
        this.lastprice = lastprice;
    }

    public String getPricingDate() {
        return pricingDate;
    }

    public void setPricingDate(String pricingDate) {
        this.pricingDate = pricingDate;
    }

    public float getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(float highPrice) {
        this.highPrice = highPrice;
    }

    public float getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(float lowPrice) {
        this.lowPrice = lowPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

