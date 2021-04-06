package com.project.crm;

import com.project.crm.domain.Dto.CurrencyDto;
import com.project.crm.service.CurrencyClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSuite {

    @Autowired
    private CurrencyClient client;

    @Test
    public void testContext() {

        List<CurrencyDto> currencies = client.getCurrency();
        System.out.println(currencies.get(0).getNo());
        currencies.get(0).getRates().stream()
                .map(rate -> rate.getCode() + " " + rate.getCurrency() + " " + rate.getMid())
                .forEach(System.out::println);

    }
}
