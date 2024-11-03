package com.bank_account.accounts.controller;

import com.bank_account.accounts.constant.AccountsConstant;
import com.bank_account.accounts.dto.CustomerDto;
import com.bank_account.accounts.dto.ErrorResponseDto;
import com.bank_account.accounts.dto.ResponseDto;
import com.bank_account.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(
        name = "CRUD REST API's for Accounts Microservices in Bank application",
        description = "CRUD REST API's in Bank application to CREATE, FETCH, UPDATE, DELETE accounts details"
)
public class AccountsController {


    private IAccountsService iAccountsService;


    @Operation(
            summary = "Create a new account",
            description = "Creates a new account based on the provided customer information"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Account created successfully"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstant.STATUS_201, AccountsConstant.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch account details",
            description = "Retrieves a customer account by their mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Account details fetched successfully"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountsDetails(@RequestParam
                                                            @Pattern(regexp = "$|[0-9]{10}", message = "Mobile number should be 10 digits")
                                                            String mobileNumber) {

        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Update account details",
            description = "Updates an existing account with the provided customer information"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account details updated successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Account details not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);

        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstant.STATUS_200, AccountsConstant.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstant.STATUS_417, AccountsConstant.MESSAGE_417_UPDATE));


        }
    }

    @Operation(
            summary = "Delete account details",
            description = "Deletes a customer account based on their mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account details updated successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Account details not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                            @Pattern(regexp = "$|[0-9]{10}", message = "Mobile number should be 10 digits")
                                                            String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccounts(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstant.STATUS_200, AccountsConstant.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstant.STATUS_417, AccountsConstant.MESSAGE_417_DELETE));
        }
    }
}
