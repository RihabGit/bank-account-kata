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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OperationServiceImpl implements OperationService{

    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    Logger LOGGER = LoggerFactory.getLogger(OperationService.class);

    public OperationServiceImpl(AccountRepository accountRepository, OperationRepository operationRepository, OperationMapper operationMapper) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
        this.operationMapper = operationMapper;
    }

    @Override
    public OperationDto deposit(Long accountId, BigDecimal amount) throws NoSuchAccountException, UnvalidOperationException {
        return executeOperation(accountId, amount, OperationTypeEnum.DEPOSIT);
    }

    @Override
    public OperationDto withdrawal(Long accountId, BigDecimal amount) throws NoSuchAccountException, UnvalidOperationException {
        return executeOperation(accountId,amount, OperationTypeEnum.WITHDRAWAL);
    }

    private void checkOpValidity(BigDecimal amount, OperationTypeEnum operationType, BigDecimal balance) {
        if (amount.intValue()<0) {
            LOGGER.error("can not {} negative amount", operationType);
            throw new UnvalidOperationException("Can not hundle negative amount");
        }
        if (OperationTypeEnum.WITHDRAWAL.equals(operationType) && amount.compareTo(balance)>0) {
            LOGGER.error("can not withdraw this amount, your balance is {}", balance );
            throw new UnvalidOperationException("Can not hundle negative amount");
        }

    }

    private OperationDto executeOperation(Long accountId, BigDecimal amount, OperationTypeEnum operationType) throws NoSuchAccountException, UnvalidOperationException {
        LOGGER.info("Start {} for account {}", operationType, accountId);
        Account account = accountRepository.findById(accountId).orElseThrow(() -> {
            LOGGER.error("Trying to {} from a non existing account with id: {}", operationType, accountId);
            return new NoSuchAccountException(accountId.toString());
        });
        checkOpValidity(amount, operationType, account.getBalance());
        Operation operation = new Operation(null, amount, operationType, null, LocalDateTime.now());
        if (operationType.equals(OperationTypeEnum.DEPOSIT)) {
            account.setBalance(account.getBalance().add(amount));
        }else{
            account.setBalance(account.getBalance().subtract(amount));
        }
        operation.setAccount(account);
        Operation savedOp = operationRepository.save(operation);
        LOGGER.info("Operation {} with id {} for account {} is saved", operationType, operation.getId(), accountId);
        OperationDto operationDto = operationMapper.mapOperationToOperationDto(savedOp);
        return operationDto;
    }
}
