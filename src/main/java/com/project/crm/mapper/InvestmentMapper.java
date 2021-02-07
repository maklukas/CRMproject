package com.project.crm.mapper;

import com.project.crm.domain.Investment;
import com.project.crm.domain.Dto.InvestmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvestmentMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private StatusMapper statusMapper;

    public Investment mapToInvestment(InvestmentDto investment) {
        return new Investment(
                investment.getId(),
                investment.getName(),
                investment.getAddress(),
                userMapper.mapToUser(investment.getRegisteredBy()),
                companyMapper.mapToCompanyList(investment.getCompanies()),
                clientMapper.mapToClientList(investment.getClients()),
                statusMapper.mapToStatus(investment.getStatus())
        );
    }

    public InvestmentDto mapToInvestmentDto(Investment investment) {
        return new InvestmentDto(
                investment.getId(),
                investment.getName(),
                investment.getAddress(),
                userMapper.mapToUserDto(investment.getRegisteredBy()),
                companyMapper.mapToCompanyDtoList(investment.getCompanies()),
                clientMapper.mapToClientDtoList(investment.getClients()),
                statusMapper.mapToStatusDto(investment.getStatus())
        );
    }

    public List<InvestmentDto> mapToInvestmentDtoList(List<Investment> investments) {
        return investments.stream()
                .map(investment -> new InvestmentDto(
                        investment.getId(),
                        investment.getName(),
                        investment.getAddress(),
                        userMapper.mapToUserDto(investment.getRegisteredBy()),
                        companyMapper.mapToCompanyDtoList(investment.getCompanies()),
                        clientMapper.mapToClientDtoList(investment.getClients()),
                        statusMapper.mapToStatusDto(investment.getStatus())
                )).collect(Collectors.toList());
    }

    public List<Investment> mapToInvestmentList(List<InvestmentDto> investments) {
        return investments.stream()
                .map(investment -> new Investment(
                        investment.getId(),
                        investment.getName(),
                        investment.getAddress(),
                        userMapper.mapToUser(investment.getRegisteredBy()),
                        companyMapper.mapToCompanyList(investment.getCompanies()),
                        clientMapper.mapToClientList(investment.getClients()),
                        statusMapper.mapToStatus(investment.getStatus())
                )).collect(Collectors.toList());
    }

}
