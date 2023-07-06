package com.example.sgKata.service;

import com.example.sgKata.domain.Account;
import com.example.sgKata.domain.Operation;
import com.example.sgKata.domain.OperationTypeEnum;
import com.example.sgKata.domain.dto.OperationDto;
import com.example.sgKata.domain.mapper.OperationMapper;
import com.example.sgKata.exception.NoSuchAccountException;
import com.example.sgKata.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    OperationMapper operationMapper;

    @InjectMocks
    AccountServiceImpl accountService;
    private Account account ;
    private List<Operation> operations;
    private List<OperationDto> operationDtos;

    @Before
    public void setUp(){
         account = new Account(12L,BigDecimal.valueOf(1000), new ArrayList<>());
        operations = new ArrayList<>();
        operationDtos = new ArrayList<>();
        operations.add(new Operation(1L, BigDecimal.valueOf(2000),OperationTypeEnum.DEPOSIT,account,LocalDateTime.now()));
        operationDtos.add(new OperationDto(1L, BigDecimal.valueOf(2000),OperationTypeEnum.DEPOSIT,LocalDateTime.now()));
        account.setOperations(operations);
    }

    @Test(expected = NoSuchAccountException.class)
    public void operationsHistory_should_throw_exception_for_no_such_account() throws NoSuchAccountException{
        Mockito.when(accountRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        accountService.operationsHistory(12L);
        Assert.fail("should have thrown NoSuchAccountException ");
    }

    @Test
    public void operationsHistory_should_return_all_account_operations() throws NoSuchAccountException {
        Mockito.when(accountRepository.findById(12L)).thenReturn(Optional.of(account));
        Mockito.when(operationMapper.mapListOperationToListOperationDto(operations)).thenReturn(operationDtos);
        List<OperationDto> foundOperations = accountService.operationsHistory(12L);
        Assertions.assertThat(foundOperations).hasSize(1);
    }

}
