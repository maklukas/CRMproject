package com.project.crm.mapper;

import com.google.gson.Gson;
import com.project.crm.domain.*;
import com.project.crm.domain.Dto.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTestSuite {

    @Autowired
    private MapperConnected mapper;

    @Test
    public void shouldMapClient() {
        //given
        Company company = new Company("NameT", "AddressT", "taxNO");
        User user = new User("Userman", "pasman", "fnT", "lnT", null);
        Investment investment = new Investment("ITest", "IAddre", user, null);

        List<Company> companies = new ArrayList<>();
        companies.add(company);
        List<Investment> investments = new ArrayList<>();
        investments.add(investment);

        Client client = new Client(1, "Lukasz", "Makuch", 880700290, companies, investments);
        //when
        ClientDto clientDto = mapper.client.mapToClientDto(client);

        System.out.println(new Gson().toJson(clientDto));
        //then
        Assert.assertEquals(client.getId(), clientDto.getId());
        Assert.assertEquals(client.getCompanies().get(0).getName(), clientDto.getCompanies().get(0).getName());
        Assert.assertEquals(client.getFirstname(), clientDto.getFirstname());
        Assert.assertEquals(client.getLastname(), clientDto.getLastname());
        Assert.assertEquals(client.getPhoneNo(), clientDto.getPhoneNo());
    }

    @Test
    public void shouldMapClientDto() {
        //given
        CompanyDto company = new CompanyDto(1, "NameT", "AddressT", "taxNO");
        UserDto user = new UserDto(1, "Userman", "fnT", "lnT", null);
        InvestmentDto investment = new InvestmentDto(1, "ITest", "IAddre", user, new ArrayList<>(), new ArrayList<>(), null);

        List<CompanyDto> companies = new ArrayList<>();
        List<InvestmentDto> investments = new ArrayList<>();
        companies.add(company);
        investments.add(investment);

        ClientDto clientDto = new ClientDto(1, "Lukasz", "Makuch", 880700290, companies);
        //when
        Client client = mapper.client.mapToClient(clientDto);

        //then
        Assert.assertEquals(client.getId(), clientDto.getId());
        Assert.assertEquals(client.getCompanies().get(0).getName(), clientDto.getCompanies().get(0).getName());
        Assert.assertEquals(client.getFirstname(), clientDto.getFirstname());
        Assert.assertEquals(client.getLastname(), clientDto.getLastname());
        Assert.assertEquals(client.getPhoneNo(), clientDto.getPhoneNo());
    }

    @Test
    public void shouldMapCompanyToDto() {
        //given
        Company company = new Company("Name", "address", "TaxNo");
        //when
        CompanyDto companyDto = mapper.company.mapToCompanyDto(company);
        //then
        Assert.assertEquals(company.getId(), companyDto.getId());
        Assert.assertEquals(company.getName(), companyDto.getName());
        Assert.assertEquals(company.getAddress(), companyDto.getAddress());
        Assert.assertEquals(company.getTaxNumber(), companyDto.getTaxNumber());
        Assert.assertEquals(companyDto.getClass(), CompanyDto.class);
    }

    @Test
    public void shouldMapDtoToCompany() {
        //given
        CompanyDto companyDto = new CompanyDto(1,"Name", "address", "TaxNo");
        //when
        Company company = mapper.company.mapToCompany(companyDto);
        //then
        Assert.assertEquals(company.getId(), companyDto.getId());
        Assert.assertEquals(company.getName(), companyDto.getName());
        Assert.assertEquals(company.getAddress(), companyDto.getAddress());
        Assert.assertEquals(company.getTaxNumber(), companyDto.getTaxNumber());
        Assert.assertEquals(company.getClass(), Company.class);
    }

    @Test
    public void shouldMapDepartmentToDto() {
        //given
        Department department = new Department("Depar");
        //when
        DepartmentDto departmentDto = mapper.department.mapToDepartmentDto(department);
        //then
        Assert.assertEquals(department.getName(), departmentDto.getName());
        Assert.assertEquals(departmentDto.getClass(), DepartmentDto.class);
    }

    @Test
    public void shouldMapDtoToDepartment() {
        //given
        DepartmentDto departmentDto = new DepartmentDto(1, "Depar");
        //when
        Department department = mapper.department.mapToDepartment(departmentDto);
        //then
        Assert.assertEquals(department.getName(), departmentDto.getName());
        Assert.assertEquals(department.getClass(), Department.class);
    }

    @Test
    public void shouldMapListDepartment() {
        //given
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        departmentDtos.add(new DepartmentDto(1, "Depar"));
        //when
        List<Department> departments = mapper.department.mapToDepartmentList(departmentDtos);
        //then
        Assert.assertEquals(1, departments.size());
        Assert.assertEquals(departmentDtos.get(0).getId(), departments.get(0).getId());
        Assert.assertEquals(departmentDtos.get(0).getName(), departments.get(0).getName());
        //cleanup
        departmentDtos = null;
        //when2
        departmentDtos = mapper.department.mapToDepartmentDtoList(departments);
        //then2
        Assert.assertEquals(1, departmentDtos.size());
        Assert.assertEquals(departmentDtos.get(0).getId(), departments.get(0).getId());
        Assert.assertEquals(departmentDtos.get(0).getName(), departments.get(0).getName());
    }

    @Test
    public void shouldMapInvestments() {
        //given
        Department department = new Department("NDep");
        User user = new User("uname", "upas", "fn", "ln", department);
        Status status = new Status("Nowy");
        Investment investment = new Investment("NTe", "NTadd", user, status);
        //when
        InvestmentDto investmentDto = mapper.investment.mapToInvestmentDto(investment);
        //then
        Assert.assertEquals(investment.getId(), investmentDto.getId());
        Assert.assertEquals(investment.getName(), investmentDto.getName());
        Assert.assertEquals(investment.getAddress(), investmentDto.getAddress());
        Assert.assertEquals(investment.getRegisteredBy().getLastname(), investmentDto.getRegisteredBy().getLastname());
        Assert.assertEquals(investment.getStatus().getName(), investmentDto.getStatus().getName());
        Assert.assertEquals(InvestmentDto.class, investmentDto.getClass());
        Assert.assertEquals(StatusDto.class, investmentDto.getStatus().getClass());
        Assert.assertEquals(UserDto.class, investmentDto.getRegisteredBy().getClass());

        //cleanUp
        investment = null;
        //when2
        investment = mapper.investment.mapToInvestment(investmentDto);
        //then
        Assert.assertEquals(investment.getId(), investmentDto.getId());
        Assert.assertEquals(investment.getName(), investmentDto.getName());
        Assert.assertEquals(investment.getAddress(), investmentDto.getAddress());
        Assert.assertEquals(investment.getRegisteredBy().getLastname(), investmentDto.getRegisteredBy().getLastname());
        Assert.assertEquals(investment.getStatus().getName(), investmentDto.getStatus().getName());
        Assert.assertEquals(Investment.class, investment.getClass());
        Assert.assertEquals(Status.class, investment.getStatus().getClass());
        Assert.assertEquals(User.class, investment.getRegisteredBy().getClass());

        //when 3
        List<Investment> investments = new ArrayList<>();
        investments.add(investment);
        List<InvestmentDto> investmentDtos = mapper.investment.mapToInvestmentDtoList(investments);
        //then 3

        Assert.assertEquals(1, investmentDtos.size());
        Assert.assertEquals(investments.get(0).getId(), investmentDtos.get(0).getId());
        Assert.assertEquals(investments.get(0).getName(), investmentDtos.get(0).getName());
        Assert.assertEquals(investments.get(0).getAddress(), investmentDtos.get(0).getAddress());
        Assert.assertEquals(investments.get(0).getRegisteredBy().getLastname(), investmentDtos.get(0).getRegisteredBy().getLastname());
        Assert.assertEquals(investments.get(0).getStatus().getName(), investmentDtos.get(0).getStatus().getName());
        Assert.assertEquals(InvestmentDto.class, investmentDtos.get(0).getClass());
        Assert.assertEquals(StatusDto.class, investmentDtos.get(0).getStatus().getClass());
        Assert.assertEquals(UserDto.class, investmentDtos.get(0).getRegisteredBy().getClass());
    }

    @Test
    public void shouldMapStatus() {
        //given
        Status status = new Status("nowy");
        //when
        StatusDto statusDto = mapper.status.mapToStatusDto(status);
        //then
        Assert.assertEquals(status.getId(), statusDto.getId());
        Assert.assertEquals(status.getName(), statusDto.getName());
        Assert.assertEquals(statusDto.getClass(), StatusDto.class);

        //cleanUp
        status = null;
        //when 2
        status = mapper.status.mapToStatus(statusDto);
        //then 2
        Assert.assertEquals(status.getId(), statusDto.getId());
        Assert.assertEquals(status.getName(), statusDto.getName());
        Assert.assertEquals(status.getClass(), Status.class);

        //when 3
        List<Status> statuses = new ArrayList<>();
        statuses.add(status);
        List<StatusDto> statusDtos = mapper.status.mapToStatusDtoList(statuses);

        //then 3
        Assert.assertEquals(1, statusDtos.size());
        Assert.assertEquals(statusDtos.get(0).getId(), statuses.get(0).getId());
        Assert.assertEquals(statusDtos.get(0).getName(), statuses.get(0).getName());
        Assert.assertEquals(statusDtos.get(0).getClass(), StatusDto.class);
    }

    @Test
    public void shouldMapTask() {
        //given
        Status status = new Status("Nowy");
        Task task = new Task("TaskN", "tDescr", LocalDateTime.now().plusDays(10), status);
        //when
        TaskDto taskDto = mapper.task.mapToTaskDto(task);
        //then
        Assert.assertEquals(task.getId(), taskDto.getId());
        Assert.assertEquals(task.getCreationTime(), taskDto.getCreationTime());
        Assert.assertEquals(task.getRealizationTime(), taskDto.getRealizationTime());
        Assert.assertEquals(task.getDescription(), taskDto.getDescription());
        Assert.assertEquals(task.getTitle(), taskDto.getTitle());
        Assert.assertEquals(task.getStatus().getName(), taskDto.getStatus().getName());
        Assert.assertEquals(taskDto.getClass(), TaskDto.class);
        //cleanUp
        task = null;
        //when 2
        task = mapper.task.mapToTask(taskDto);
        //then 2
        Assert.assertEquals(task.getId(), taskDto.getId());
        Assert.assertEquals(task.getCreationTime(), taskDto.getCreationTime());
        Assert.assertEquals(task.getRealizationTime(), taskDto.getRealizationTime());
        Assert.assertEquals(task.getDescription(), taskDto.getDescription());
        Assert.assertEquals(task.getTitle(), taskDto.getTitle());
        Assert.assertEquals(task.getStatus().getName(), taskDto.getStatus().getName());
        Assert.assertEquals(task.getClass(), Task.class);
        //when 3
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        List<TaskDto> taskDtos = mapper.task.mapToTaskDtoList(tasks);
        //then 3
        Assert.assertEquals(1, taskDtos.size());
        Assert.assertEquals(tasks.get(0).getId(), taskDtos.get(0).getId());
        Assert.assertEquals(tasks.get(0).getCreationTime(), taskDtos.get(0).getCreationTime());
        Assert.assertEquals(tasks.get(0).getRealizationTime(), taskDtos.get(0).getRealizationTime());
        Assert.assertEquals(tasks.get(0).getDescription(), taskDtos.get(0).getDescription());
        Assert.assertEquals(tasks.get(0).getTitle(), taskDtos.get(0).getTitle());
        Assert.assertEquals(tasks.get(0).getStatus().getName(), taskDtos.get(0).getStatus().getName());
        Assert.assertEquals(taskDtos.get(0).getClass(), TaskDto.class);
    }

    @Test
    public void shouldMapUser() {
        //given
        Department department = new Department("Depar");
        User user = new User("Una", "pase", "ln", "fn", department);
        //when
        UserDto userDto = mapper.user.mapToUserDto(user);
        //then
        Assert.assertEquals(user.getId(), userDto.getId());
        Assert.assertEquals(user.getLastname(), userDto.getLastname());
        Assert.assertEquals(user.getFirstname(), userDto.getFirstname());
        Assert.assertEquals(user.getUsername(), userDto.getUsername());
        Assert.assertEquals(user.getDepartment().getName(), userDto.getDepartment().getName());
        Assert.assertEquals(userDto.getDepartment().getClass(), DepartmentDto.class);
        Assert.assertEquals(userDto.getClass(), UserDto.class);
        //cleanUp
        user = null;
        //when 2
        user = mapper.user.mapToUser(userDto);
        //then 2
        Assert.assertEquals(user.getId(), userDto.getId());
        Assert.assertEquals(user.getLastname(), userDto.getLastname());
        Assert.assertEquals(user.getFirstname(), userDto.getFirstname());
        Assert.assertEquals(user.getUsername(), userDto.getUsername());
        Assert.assertEquals(user.getDepartment().getName(), userDto.getDepartment().getName());
        Assert.assertEquals(user.getDepartment().getClass(), Department.class);
        Assert.assertEquals(user.getClass(), User.class);
        //when 3
        List<User> users = new ArrayList<>();
        users.add(user);
        List<UserDto> userDtos = mapper.user.mapToUserDtoList(users);
        //then 3
        Assert.assertEquals(1, userDtos.size());
        Assert.assertEquals(users.get(0).getId(), userDtos.get(0).getId());
        Assert.assertEquals(users.get(0).getLastname(), userDtos.get(0).getLastname());
        Assert.assertEquals(users.get(0).getFirstname(), userDtos.get(0).getFirstname());
        Assert.assertEquals(users.get(0).getUsername(), userDtos.get(0).getUsername());
        Assert.assertEquals(users.get(0).getDepartment().getName(), userDtos.get(0).getDepartment().getName());
        Assert.assertEquals(userDtos.get(0).getDepartment().getClass(), DepartmentDto.class);
        Assert.assertEquals(userDtos.get(0).getClass(), UserDto.class);
    }

}
