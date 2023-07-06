package com.example.sgKata.service;

import com.example.sgKata.domain.Account;
import com.example.sgKata.domain.Operation;
import com.example.sgKata.domain.OperationTypeEnum;
import com.example.sgKata.domain.dto.OperationDto;
import com.example.sgKata.domain.mapper.OperationMapper;
import com.example.sgKata.exception.NoSuchAccountException;
import com.example.sgKata.exception.UnvalidOperationException;
import com.example.sgKata.repository.AccountRepository;
import com.example.sgKata.repository.OperationRepository;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
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
public class OperationServiceTest {

    @Mock
    AccountRepository accountRepository;
    @Mock
    OperationRepository operationRepository;
    @Mock
    OperationMapper operationMapper;
    @InjectMocks
    OperationServiceImpl operationService;

    private Account account ;
    private Operation operation;
    private OperationDto operationDto;

    @Before
    public void setUp(){
        account = new Account(12L, BigDecimal.valueOf(1000), new ArrayList<>());
        operation = new Operation(1L, BigDecimal.valueOf(1200),OperationTypeEnum.DEPOSIT,account,LocalDateTime.now());
        operationDto = new OperationDto(1L, BigDecimal.valueOf(1200),OperationTypeEnum.DEPOSIT,LocalDateTime.now());
    }

    @Test(expected = NoSuchAccountException.class)
    public void deposit_should_throw_NoSuchAccountException() throws NoSuchAccountException {
        Mockito.when(accountRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        operationService.deposit(12L,BigDecimal.valueOf(1200));
        Assert.fail("should have thrown NoSuchAccountException ");
    }

   @Test(expected = UnvalidOperationException.class)
    public void deposit_should_throw_UnvalidOperationException() throws UnvalidOperationException {
       Mockito.when(accountRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(account));
        operationService.deposit(12L,BigDecimal.valueOf(-200));
        Assert.fail("should have thrown UnvalidOperationException ");
    }


    @Test
    public void deposit_should_save_op() throws NoSuchAccountException {
        Mockito.when(accountRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(account));
        Mockito.when(operationMapper.mapOperationToOperationDto(operation)).thenReturn(operationDto);
        Mockito.when(operationRepository.save(ArgumentMatchers.any(Operation.class))).thenReturn(operation);
        OperationDto currentOperationDto = operationService.deposit(12L,BigDecimal.valueOf(1200));
        Assertions.assertThat(currentOperationDto).isNotNull();
        Assertions.assertThat(currentOperationDto.getType()).isEqualTo(OperationTypeEnum.DEPOSIT);
        Assertions.assertThat(currentOperationDto.getAmount()).isEqualTo(BigDecimal.valueOf(1200));
    }

    @Test(expected = NoSuchAccountException.class)
    public void withdrawal_should_throw_NoSuchAccountException() throws NoSuchAccountException {
        Mockito.when(accountRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        operationService.withdrawal(12L,BigDecimal.valueOf(1200));
        Assert.fail("should have thrown NoSuchAccountException ");
    }

    @Test(expected = UnvalidOperationException.class)
    public void withdrawal_should_throw_UnvalidOperationException() throws UnvalidOperationException {
        Mockito.when(accountRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(account));
        operationService.withdrawal(12L,BigDecimal.valueOf(-200));
        Assert.fail("should have thrown UnvalidOperationException ");
    }


}
