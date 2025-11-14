package com.mercadohoy.mercadohoybackend.crypto.dto;

public class CryptoAsset {

    private String id;                 // por ejemplo "BTCUSDT"
    private String symbol;             // "BTC"
    private String name;               // "Bitcoin" (por ahora igual que symbol)
    private double priceUsd;           // precio en USDT ~ USD
    private double changePercent24Hr;  // % cambio 24h

    public CryptoAsset() {
    }

    public CryptoAsset(String id, String symbol, String name,
                       double priceUsd, double changePercent24Hr) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.priceUsd = priceUsd;
        this.changePercent24Hr = changePercent24Hr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public double getChangePercent24Hr() {
        return changePercent24Hr;
    }

    public void setChangePercent24Hr(double changePercent24Hr) {
        this.changePercent24Hr = changePercent24Hr;
    }
}
