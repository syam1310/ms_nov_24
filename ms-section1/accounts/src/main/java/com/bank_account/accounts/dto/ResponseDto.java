package com.bank_account.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "ResponseDto")
public class ResponseDto {

    @Schema(name = "Status Code")
    private String statusCode;
    @Schema(name = "Status Message")
    private String statusMsg;
}
