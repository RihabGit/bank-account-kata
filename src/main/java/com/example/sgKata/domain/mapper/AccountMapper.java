package com.example.sgKata.domain.mapper;

import com.example.sgKata.domain.Account;
import com.example.sgKata.domain.dto.AccountDto;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface AccountMapper {

    public AccountDto mapAccountToAccountDto(Account account);
}
