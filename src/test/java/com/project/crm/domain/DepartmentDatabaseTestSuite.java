package com.project.crm.domain;

import com.project.crm.repository.DepartmentRepository;
import com.project.crm.service.ServiceConnected;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentDatabaseTestSuite {

    @Autowired
    private ServiceConnected service;

    @Test
    public void shouldCreateDepartment() {
        //given
        Department department = new Department("Dział handlowy");
        Department department2 = new Department("Dział handlowy");
        Department department3 = new Department("Dział eksportu");
        //when
        service.department.createDepartment(department);
        service.department.createDepartment(department2);
        service.department.createDepartment(department3);
        //then
        Assert.assertEquals(2, service.department.getDepartments().size());
        //cleanup
        service.department.deleteDepartment(department.getId());
        service.department.deleteDepartment(department2.getId());
        service.department.deleteDepartment(department3.getId());
    }


}
