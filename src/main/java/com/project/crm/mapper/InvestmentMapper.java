package com.project.crm.mapper;

import com.project.crm.domain.Investment;
import com.project.crm.domain.Dto.InvestmentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvestmentMapper {

    private static Logger LOGGER = LoggerFactory.getLogger(InvestmentMapper.class);

    @Autowired
    private MapperConnected mapper;

    public Investment mapToInvestment(InvestmentDto investment) {
        if (investment == null) {
            LOGGER.error("Mapping filed..");
            return new Investment();
        } else {
            return new Investment(
                    investment.getId(),
                    investment.getName(),
                    investment.getAddress(),
                    mapper.user.mapToUser(investment.getRegisteredBy()),
                    mapper.company.mapToCompanyList(investment.getCompanies()),
                    mapper.client.mapToClientList(investment.getClients()),
                    mapper.status.mapToStatus(investment.getStatus())
            );
        }
    }

    public InvestmentDto mapToInvestmentDto(Investment investment) {
        if (investment == null) {
            LOGGER.error("Mapping filed..");
            return new InvestmentDto();
        } else {
            return new InvestmentDto(
                    investment.getId(),
                    investment.getName(),
                    investment.getAddress(),
                    mapper.user.mapToUserDto(investment.getRegisteredBy()),
                    mapper.company.mapToCompanyDtoList(investment.getCompanies()),
                    mapper.client.mapToClientDtoList(investment.getClients()),
                    mapper.status.mapToStatusDto(investment.getStatus())
            );
        }
    }

    public List<InvestmentDto> mapToInvestmentDtoList(List<Investment> investments) {
        if (investments == null) {
            LOGGER.error("Mapping filed..");
            return new ArrayList<>();
        } else {
            return investments.stream()
                    .map(investment -> new InvestmentDto(
                            investment.getId(),
                            investment.getName(),
                            investment.getAddress(),
                            mapper.user.mapToUserDto(investment.getRegisteredBy()),
                            mapper.company.mapToCompanyDtoList(investment.getCompanies()),
                            mapper.client.mapToClientDtoList(investment.getClients()),
                            mapper.status.mapToStatusDto(investment.getStatus())
                    )).collect(Collectors.toList());
        }
    }

    public List<Investment> mapToInvestmentList(List<InvestmentDto> investments) {
        if (investments == null) {
            LOGGER.error("Mapping filed..");
            return new ArrayList<>();
        } else {
            return investments.stream()
                    .map(investment -> new Investment(
                            investment.getId(),
                            investment.getName(),
                            investment.getAddress(),
                            mapper.user.mapToUser(investment.getRegisteredBy()),
                            mapper.company.mapToCompanyList(investment.getCompanies()),
                            mapper.client.mapToClientList(investment.getClients()),
                            mapper.status.mapToStatus(investment.getStatus())
                    )).collect(Collectors.toList());
        }
    }

}
