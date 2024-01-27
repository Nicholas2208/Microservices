package com.nwhite.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountDto {

    @Schema(
            description = "Account Number of White Bank account", example = "3454433243"
    )
    @NotEmpty(message = "AccountNumber must not be null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of NWhite Bank account", example = "Savings"
    )
    @NotEmpty(message = "AccountType must not be null or empty")
    private String accountType;

    @Schema(
            description = "White Bank branch address", example = "555 St.-Petersburg"
    )
    @NotEmpty(message = "BranchAddress must not be null or empty")
    private String branchAddress;
}
