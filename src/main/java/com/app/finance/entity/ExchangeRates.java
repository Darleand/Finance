package com.app.finance.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExchangeRates {
    private Map<String, Double> rates = new LinkedHashMap<>();
    private double exchangeRates;
    private String date;
    private String typeCurrency;

    public String getTypeCurrency() {
        return typeCurrency;
    }

    public void setTypeCurrency(String typeCurrency) {
        this.typeCurrency = typeCurrency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public void addRatesList(ExchangeRates exchangeRates) throws Exception {
        if (exchangeRates == null) {
            throw new Exception();
        } else {
            rates.put(exchangeRates.getDate(), exchangeRates.getExchangeRates());
        }
    }

    public Double getExchangeRates() {
        return exchangeRates;
    }

    public void setExchangeRates(Double exchangeRates) {
        this.exchangeRates = exchangeRates;
    }
}
