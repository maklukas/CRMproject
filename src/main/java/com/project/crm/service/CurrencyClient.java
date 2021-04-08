package com.project.crm.service;

import com.project.crm.domain.Dto.CurrencyDto;
import com.project.crm.domain.Dto.RateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyClient {

    private RestTemplate restTemplate;
    private static final URI URL = UriComponentsBuilder.fromHttpUrl("http://api.nbp.pl/api/exchangerates/tables/A")
            .queryParam("format", "json")
            .build().encode().toUri();

    @Autowired
    public CurrencyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CurrencyDto> getCurrency() {
        List<CurrencyDto> currencyDtoList;
        CurrencyDto[] currenciesTab = restTemplate.getForObject(URL, CurrencyDto[].class);
        currencyDtoList = Arrays.asList(currenciesTab.clone());
        return currencyDtoList;
    }

    public List<RateDto> getRate(String currency) {
        return getCurrency().stream()
                .flatMap(rate -> rate.getRates().stream()
                        .filter(x -> x.getCode().equals(currency.toUpperCase())
                        )
                ).collect(Collectors.toList());
    }
}
