package com.example.sgKata.service;

import com.example.sgKata.domain.Account;
import com.example.sgKata.domain.dto.OperationDto;
import com.example.sgKata.domain.mapper.OperationMapper;
import com.example.sgKata.repository.AccountRepository;
import com.example.sgKata.exception.NoSuchAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

    Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;
    private final OperationMapper operationMapper;

    public AccountServiceImpl(AccountRepository accountRepository, OperationMapper operationMapper) {
        this.accountRepository = accountRepository;
        this.operationMapper = operationMapper;
    }

    @Override
    public List<OperationDto> operationsHistory(long accountId) throws NoSuchAccountException {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> {
            return new NoSuchAccountException(""+accountId);
        });
        LOGGER.info("get operations history for account {}", accountId);
        return operationMapper.mapListOperationToListOperationDto(account.getOperations());
    }

}
