package com.example.sgKata.service;

import com.example.sgKata.domain.dto.AccountDto;

import java.math.BigDecimal;

public interface DevService {
    public AccountDto createAccount(BigDecimal balance);
}
