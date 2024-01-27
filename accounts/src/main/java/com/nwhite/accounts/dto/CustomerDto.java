package com.nwhite.accounts.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private String name;
    private String email;
    private String mobileNumber;
    private AccountDto accountDto;
}