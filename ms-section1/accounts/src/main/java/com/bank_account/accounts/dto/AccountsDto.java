package com.bank_account.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Schema(
        name = "Accounts",
        description = "Accounts Details"
)
public class AccountsDto {

    @Schema(
            name = "Account Number of Customer",
            description = "Account Number of Customer"
    )
    @NotEmpty(message = "Account number cannot be empty or Null")
    @Pattern(regexp = "$|[0-9]{10}", message = "Mobile number should be 10 digits")
    private Long accountNumber;

    @Schema(
            name = "Account Type of Customer",
            description = "Account Type of Customer", example = "Saving"

    )
    @NotEmpty(message = "Account type cannot be empty or Null")
    private String accountType;

    @Schema(
            name = "Branch Address of Customer",
            description = "Branch Address of Customer", example = "Ambedkar Nagar"
    )
    @NotEmpty(message = "Branch address cannot be empty or Null")
    private String branchAddress;
}
