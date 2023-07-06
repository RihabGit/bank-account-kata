package com.example.sgKata.service;

import com.example.sgKata.domain.Operation;
import com.example.sgKata.domain.dto.AccountDto;
import com.example.sgKata.domain.dto.OperationDto;
import com.example.sgKata.exception.UnvalidOperationException;
import com.example.sgKata.exception.NoSuchAccountException;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<OperationDto> operationsHistory(long accountId) throws NoSuchAccountException;

}
