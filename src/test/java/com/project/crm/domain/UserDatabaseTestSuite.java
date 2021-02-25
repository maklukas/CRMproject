package com.project.crm.domain;

import com.project.crm.service.ServiceConnected;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDatabaseTestSuite {

    @Autowired
    private ServiceConnected service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldCreateUser() {
        //given
        Department department = new Department("Dział test");
        User user = new User("login", "password", "fn", "ln", department);
        User user2 = new User("login", "otherPass123", "first name", "last name", department);
        //when
        int resultDepartment = service.department.getDepartments().size() + 1;
        int resultUser = service.user.getUsers().size() + 1;
        service.user.createUser(user);
        service.user.createUser(user2);
        //then
        Assert.assertEquals(resultDepartment, service.department.getDepartments().size());
        Assert.assertEquals(resultUser, service.user.getUsers().size());
        Assert.assertTrue(passwordEncoder.matches("password", service.user.getUserById(user.getId()).getPassword()));
        //cleanup
//        service.department.deleteDepartment(department.getId());
//        service.user.deleteUser(user.getId());
    }

}
