package com.example.sgKata.service;

import com.example.sgKata.domain.dto.OperationDto;
import com.example.sgKata.exception.NoSuchAccountException;
import com.example.sgKata.exception.UnvalidOperationException;

import java.math.BigDecimal;

public interface OperationService {

    public OperationDto deposit(Long accountId, BigDecimal amount) throws NoSuchAccountException, UnvalidOperationException;
    public OperationDto withdrawal(Long accountId, BigDecimal amount) throws NoSuchAccountException, UnvalidOperationException;
}
