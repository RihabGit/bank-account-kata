package com.example.sgKata.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private OperationTypeEnum type;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private LocalDateTime date;

}
