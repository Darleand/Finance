package com.app.finance;

import com.app.finance.entity.RateParam;
import com.app.finance.graph.GraphRate;
import com.app.finance.service.AppExchangeRates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class FinanceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FinanceApplication.class, args);
        System.setProperty("java.awt.headless", "false");
        AppExchangeRates appExchangeRates = new AppExchangeRates();

        RateParam rateParam = new RateParam("431", "2021-09-29", "2021-11-04");
        Map<String, Double> list = appExchangeRates.getAppExchangeRates(rateParam);
        GraphRate graphRate = new GraphRate(list, rateParam);
        graphRate.createGraph(list, rateParam);
        //list.forEach((s, a) -> System.out.println(s + ":" + a));
    }

}
