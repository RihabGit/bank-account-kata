package com.example.sgKata.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OperationTypeEnum {

    DEPOSIT("deposit"), WITHDRAWAL("withdrawal");

     String type;

}
