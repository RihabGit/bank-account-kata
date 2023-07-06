package com.example.sgKata.domain.mapper;

import com.example.sgKata.domain.Operation;
import com.example.sgKata.domain.dto.OperationDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OperationMapper {

    public OperationDto mapOperationToOperationDto(Operation operation);
    public List<OperationDto> mapListOperationToListOperationDto(List<Operation> operations);
}
