package com.bank_account.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Error Response")
public class ErrorResponseDto {

    @Schema(name = "Api Path", example = "/api/v1/customers", description = "Api Path invoked by client")
    private String apiPath;
    @Schema(description = "Error Code")
    private HttpStatus errorCode;
    @Schema(description = "Error Message")
    private String errorMessage;
    @Schema(description = "Error Time")
    private LocalDateTime errorTime;
}
