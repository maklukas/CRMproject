package com.project.crm.domain;

import com.project.crm.service.ServiceConnected;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDatabaseTestSuite {

    @Autowired
    private ServiceConnected service;

    @Test
    public void shouldCreateUser() {
        //given
        Department department = new Department("Dział wdrożeń");
        User user = new User("login", "password", "fn", "ln", department);
        User user2 = new User("login", "otherPass123", "first name", "last name", department);
        //when
        service.user.createUser(user);
        service.user.createUser(user2);
        //then
        Assert.assertEquals(1, service.department.getDepartments().size());
        Assert.assertEquals(1, service.user.getUsers().size());
        //cleanup
//        service.department.deleteDepartment(department.getId());
//        service.user.deleteUser(user.getId());
    }

}
