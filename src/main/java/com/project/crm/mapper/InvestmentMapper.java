package com.project.crm.mapper;

import com.project.crm.domain.Investment;
import com.project.crm.domain.Dto.InvestmentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvestmentMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Investment mapToInvestment(InvestmentDto investment) {
        return modelMapper.map(investment, Investment.class);
    }

    public InvestmentDto mapToInvestmentDto(Investment investment) {
        return modelMapper.map(investment, InvestmentDto.class);
    }

    public List<Investment> mapToInvestmentList(List<InvestmentDto> investments) {
        return investments.stream()
                .map(investment -> modelMapper.map(investment, Investment.class))
                .collect(Collectors.toList());
    }

    public List<InvestmentDto> mapToInvestmentDtoList(List<Investment> investments) {
        return investments.stream()
                .map(investment -> modelMapper.map(investment, InvestmentDto.class))
                .collect(Collectors.toList());
    }

}
