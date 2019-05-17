package com.example.deepgandhi.stockappfrags.Models;

public class Stocks {
    private String symbol;
    private boolean isCustom;
    private String name;
    private String primaryExchange;
    private String exchange;
    private String secType;
    private String issuer;
    private String currency;
    private int id;

    public Stocks(String symbol, boolean isCustom, String name, String primaryExchange, String exchange, String secType, String issuer, String currency, int id) {
        this.symbol = symbol;
        this.isCustom = isCustom;
        this.name = name;
        this.primaryExchange = primaryExchange;
        this.exchange = exchange;
        this.secType = secType;
        this.issuer = issuer;
        this.currency = currency;
        this.id = id;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
