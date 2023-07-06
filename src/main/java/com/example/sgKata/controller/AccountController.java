package com.example.sgKata.controller;

import com.example.sgKata.domain.dto.OperationDto;
import com.example.sgKata.exception.NoSuchAccountException;
import com.example.sgKata.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/account")
@Tag(name = "Bank Account API", description = "APIs for bank account management")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "showOperationsList",description = "Lists all given account operations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Error in finding account"),
    })
    @GetMapping("/{accountId}/history")
    public List<OperationDto> showOperationsHistory(@PathVariable long accountId) throws NoSuchAccountException {
        return accountService.operationsHistory(accountId);
    }


}
