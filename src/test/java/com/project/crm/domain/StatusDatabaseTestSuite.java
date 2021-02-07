package com.project.crm.domain;

import com.project.crm.service.ServiceConnected;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatusDatabaseTestSuite {

    @Autowired
    private ServiceConnected service;

    @Test
    public void shouldCreateStatus() {
        //given
        Status status = new Status("Nowy");
        Status status2 = new Status("Nowy");
        Status status3 = new Status("Aktywny");
        //when
        service.status.createStatus(status);
        service.status.createStatus(status2);
        service.status.createStatus(status3);
        //then
        Assert.assertEquals(2, service.status.getStatuses().size());
        //cleanup
        service.status.deleteStatus(status.getId());
        service.status.deleteStatus(status2.getId());
        service.status.deleteStatus(status3.getId());
    }
}
