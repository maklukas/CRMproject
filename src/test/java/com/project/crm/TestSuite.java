package com.project.crm;

import com.project.crm.service.CurrencyClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSuite {

    @Autowired
    private CurrencyClient client;

    @Test
    public void testContext() {

    }
}
