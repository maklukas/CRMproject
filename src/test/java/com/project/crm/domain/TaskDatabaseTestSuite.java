package com.project.crm.domain;

import com.project.crm.service.ServiceConnected;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskDatabaseTestSuite {

    @Autowired
    private ServiceConnected service;

    @Test
    public void shouldCreateTask() {
        //given
        LocalDateTime realisation = LocalDateTime.now().plusDays(5);
        Department department = new Department("Test Department");

        List<User> users = new ArrayList<>();
        User user = new User("test", "test", "TestName", "TestLastname");
        user.setRole(new Role("ADMIN"));
        user.setDepartment(department);
        user.setConfirmPassword(user.getPassword());
        users.add(user);

        Task task = new Task("Test Title", "Test Description", realisation);
        task.setUsers(users);

        //when
      //  service.department.createDepartment(department);
        service.user.createUser(user);
        task.setUsers(users);
        service.task.createTask(task);
        //then
//        Assert.assertEquals(1, service.status.getStatuses().size());
//        Assert.assertEquals(1, service.department.getDepartments().size());
//        Assert.assertEquals(1, service.user.getUsers().size());
//        Assert.assertEquals(1, service.task.getTasks().size());
//        //cleanup
//        service.status.deleteStatus(status.getId());
//        service.department.deleteDepartment(department.getId());
//        service.user.deleteUser(user.getId());
//        service.task.deleteTask(task.getId());
    }

    @Test
    public void tes() {
        Task task = new Task("Test", "Test", LocalDateTime.now().plusDays(1));
        System.out.println(task.getRealizationTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
    }


}
