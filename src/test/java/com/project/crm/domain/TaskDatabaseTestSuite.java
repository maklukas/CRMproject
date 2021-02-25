package com.project.crm.domain;

import com.project.crm.service.ServiceConnected;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskDatabaseTestSuite {

    @Autowired
    private ServiceConnected service;

    @Test
    public void shouldCreateTask() {
        //given
        LocalDateTime realisation = LocalDateTime.now().plusDays(5);
        Status status = new Status("Active");
        Department department = new Department("Export");
        User user = new User("uname", "pass", "fn", "ln", department);
        Task task = new Task("Zadania", "Opis", realisation, status);
        task.addUser(user);
        //when
        service.task.createTask(task);
        //then
        Assert.assertEquals(1, service.status.getStatuses().size());
        Assert.assertEquals(1, service.department.getDepartments().size());
        Assert.assertEquals(1, service.user.getUsers().size());
        Assert.assertEquals(1, service.task.getTasks().size());
        //cleanup
        service.status.deleteStatus(status.getId());
        service.department.deleteDepartment(department.getId());
        service.user.deleteUser(user.getId());
        service.task.deleteTask(task.getId());
    }
}
