package com.nwhite.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {

    @NotEmpty(message = "AccountNumber must not be null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "AccountType must not be null or empty")
    private String accountType;

    @NotEmpty(message = "BranchAddress must not be null or empty")
    private String branchAddress;
}
