package com.bank_account.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(
        name = "Customer",
        description = "Customer Details"
)
public class CustomerDto {


    @Schema(
            name = "Name of the Customer",
            example = "John Doe",
            required = true
    )
    @NotEmpty(message = "Name cannot be empty or Null")
    @Size(min = 5, max = 30, message = "The length of customer name should be between 5 and 30")
    private String name;
    @Schema(
            name = "Email of the Customer",
            example = "psxxx0@example.com",
            required = true
    )
    @NotEmpty(message = "Email cannot be empty or Null")
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(regexp = "$|[0-9]{10}", message = "Mobile number should be 10 digits")
    @Schema(
            name = "Mobile NUmber of the Customer",
            example = "9864228323",
            required = true
    )
    private String mobileNumber;

    @Schema(
            name = "Accounts",
            description = "Account Details of Customer"
    )
    private AccountsDto accountsDto;
}
