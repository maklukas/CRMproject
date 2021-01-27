package com.project.crm.mapper;

import com.project.crm.domain.Investment;
import com.project.crm.domain.InvestmentDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvestmentMapper {
    public Investment mapToInvestment(InvestmentDto investmentDto) {
        return new Investment(
          investmentDto.getId(),
          investmentDto.getName(),
          investmentDto.getAddress(),
          investmentDto.getRegisteredBy(),
          investmentDto.getCompany(),
          investmentDto.getStatus()
        );
    }
    public InvestmentDto mapToInvestmentDto(Investment investment) {
        return new InvestmentDto(
                investment.getId(),
                investment.getName(),
                investment.getAddress(),
                investment.getRegisteredBy(),
                investment.getCompany(),
                investment.getStatus()
        );
    }
    public List<InvestmentDto> mapToInvestmentDtoList(List<Investment> investmentList) {
        return investmentList.stream()
                .map(investment -> new InvestmentDto(
                        investment.getId(),
                        investment.getName(),
                        investment.getAddress(),
                        investment.getRegisteredBy(),
                        investment.getCompany(),
                        investment.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
