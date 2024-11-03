package com.bank_account.accounts.exception;

import com.bank_account.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> validationsErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach(objectError -> {
            String fieldName = ((org.springframework.validation.FieldError) objectError).getField();
            String message = objectError.getDefaultMessage();
            validationsErrors.put(fieldName, message);
        });
        return new ResponseEntity<>(validationsErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobleException(Exception e, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerAlreadyExitsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExitsException(CustomerAlreadyExitsException e, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> resourceNotFoundException(ResourseNotFoundException e, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
