package com.example.sgKata.service;

import com.example.sgKata.domain.Account;
import com.example.sgKata.domain.dto.AccountDto;
import com.example.sgKata.domain.mapper.AccountMapper;
import com.example.sgKata.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DevServiceImpl implements DevService{

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public DevServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDto createAccount(BigDecimal balance) {
        Account account = new Account();
        account.setBalance(balance);
        accountRepository.save(account);
        AccountDto dto = accountMapper.mapAccountToAccountDto(account);
        return dto;
    }
}
