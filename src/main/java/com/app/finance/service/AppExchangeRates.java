package com.app.finance.service;

import com.app.finance.entity.RateParam;
import com.app.finance.web.MyEntityNotFoundException;
import com.app.finance.entity.ExchangeRates;
import com.app.finance.web.NotFormatTypeCurrencyException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

@Component
public class AppExchangeRates {

    public Map<String, Double> getAppExchangeRates(RateParam rateParam) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        JsonFactory jsonFactory = new JsonFactory();
        ExchangeRates exchangeRates = new ExchangeRates();
        ExchangeRates ex = new ExchangeRates();

        exchangeRates.setTypeCurrency(rateParam.getType());
        String url = String.valueOf(rateBuild(rateParam));
        String rates;
        try {
            rates = restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException e) {
            throw new HttpClientErrorException(e.getStatusCode());
        }
        check(rates);

        JsonParser parser = jsonFactory.createParser(rates);
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            parser.nextToken();
            if (parser.currentToken() == JsonToken.END_ARRAY) {
                break;
            }
            if (parser.currentToken() != JsonToken.END_OBJECT) {
                String curOfficialRate = parser.getCurrentName();
                if ("Date".equals(curOfficialRate)) {
                    ex = new ExchangeRates();
                    ex.setDate(parser.getText().substring(0, 10));
                } else {
                    if ("Cur_OfficialRate".equals(curOfficialRate)) {
                        ex.setExchangeRates(parser.getDoubleValue());
                        exchangeRates.addRatesList(ex);
                    }
                }
            }
        }
        return exchangeRates.getRates();
    }

    private void check(String str) throws MyEntityNotFoundException {
        if (str.equals("[]")) {
            throw new MyEntityNotFoundException();
        }
    }

    private StringBuilder rateBuild(RateParam rateParam) throws DataFormatException {
        StringBuilder urlBuilder = new StringBuilder("https://www.nbrb.by/api/exrates/rates/dynamics/");
        Pattern pattern = Pattern.compile("^[0-9]{3}$");
        Matcher matcher = pattern.matcher(rateParam.getType());
        if (matcher.find()) {
            urlBuilder.append(rateParam.getType());
        } else {
            throw new NotFormatTypeCurrencyException();
        }
        //
        pattern = Pattern.compile("^[0-2]\\d\\d\\d-[0-3]\\d-[0-3]\\d$");
        matcher = pattern.matcher(rateParam.getStart());
        if (matcher.find()) {
            matcher = pattern.matcher(rateParam.getEnd());
            if (matcher.find()) {
                urlBuilder.append("?startdate=").append(rateParam.getStart()).append("&enddate=").append(rateParam.getEnd());
            }
        } else {
            throw new DataFormatException();
        }
        return urlBuilder;
    }
}
