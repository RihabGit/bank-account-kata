package com.example.sgKata.domain.dto;

import com.example.sgKata.domain.OperationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto {
    private Long id;
    private BigDecimal amount;
    private OperationTypeEnum type;
    private LocalDateTime date;
}
