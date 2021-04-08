package com.project.crm.controller;

import com.project.crm.domain.Dto.CurrencyDto;
import com.project.crm.domain.Dto.RateDto;
import com.project.crm.service.CurrencyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/currencies")
@CrossOrigin("*")
public class CurrencyController {

    private CurrencyClient currencyClient;

    @Autowired
    public CurrencyController(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    @GetMapping
    public List<CurrencyDto> getCurrencies() {
        return currencyClient.getCurrency();
    }

    @GetMapping(params = "code")
    public List<RateDto> getRate(@RequestParam String code) {
        return currencyClient.getRate(code);
    }
}
