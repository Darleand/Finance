package com.app.finance.controller;

import com.app.finance.entity.RateParam;
import com.app.finance.service.AppExchangeRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
@RequestMapping("/web/finance")
public class MainController {
    @Autowired
    AppExchangeRates appExchangeRates;

    @GetMapping("/rateforperiod")
    public Map<String,Double> getRate(
            @RequestParam String type, String start, String end) throws Exception {
        RateParam rateParam = new RateParam(type, start, end);
        return appExchangeRates.getAppExchangeRates(rateParam);
    }
}
