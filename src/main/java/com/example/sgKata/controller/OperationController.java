package com.example.sgKata.controller;

import com.example.sgKata.domain.dto.OperationDto;
import com.example.sgKata.domain.dto.AmountDto;
import com.example.sgKata.exception.NoSuchAccountException;
import com.example.sgKata.exception.UnvalidOperationException;
import com.example.sgKata.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operation")
@Tag(name = "Operations APIs", description = "APIs for operations management")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PutMapping(value = "/{accountId}/deposit")
    @Operation(summary = "depositMoneyInBankAccount",description = "Execute deposit operation on a given account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Error in finding account"),
    })
    public ResponseEntity<OperationDto> deposit(@PathVariable long accountId,
                                                @RequestBody AmountDto amountDto) throws NoSuchAccountException, UnvalidOperationException {
        return new ResponseEntity<>(operationService.deposit(accountId,amountDto.getAmount()), HttpStatus.OK);
    }

    @PutMapping(value = "/{accountId}/withdrawal")
    @Operation(summary = "retrieveMoneyFromBankAccount",description = "execute withdrawal operation on a given account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Error in finding account"),
    })
    public ResponseEntity<OperationDto> withdrawal(@PathVariable long accountId,
                                                      @RequestBody AmountDto amountDto) throws NoSuchAccountException, UnvalidOperationException {
        return new ResponseEntity<>(operationService.withdrawal(accountId,amountDto.getAmount()), HttpStatus.OK);
    }
}
