package com.example.sgKata.controller;

import com.example.sgKata.domain.dto.AccountDto;
import com.example.sgKata.service.DevService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev")
@Tag(name = "Developer API", description = "This API is added for test purpose")
public class DevContoller {

    private final DevService devService;

    public DevContoller(DevService devService) {
        this.devService = devService;
    }

    @PostMapping("/create-bank-account")
    @Operation(summary = "createBankAccount",description = "Create bank account API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(devService.createAccount(accountDto.getBalance()), HttpStatus.CREATED);
    }
}
