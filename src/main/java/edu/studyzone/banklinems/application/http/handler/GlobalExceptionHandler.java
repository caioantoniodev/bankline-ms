package edu.studyzone.banklinems.application.http.handler;

import edu.studyzone.banklinems.application.dto.ErrorResponse;
import edu.studyzone.banklinems.infra.exception.ChargeTypeNotFoundException;
import edu.studyzone.banklinems.infra.exception.ExceptionCodes;
import edu.studyzone.banklinems.infra.exception.InsufficientBalanceException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${exception.trace:false}")
    private boolean printStackTrace;

    @ExceptionHandler(InsufficientBalanceException.class)
    protected ResponseEntity<ErrorResponse> processInsufficientBalanceException(final InsufficientBalanceException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionCodes.GENERIC_INSUFFICIENT_BALANCE_EXCEPTION);
        errorResponse.setMessage(ex.getMessage());

        return this.createBodyResponseError(ex, errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ChargeTypeNotFoundException.class)
    protected ResponseEntity<ErrorResponse> processChargeTypeNotFoundException(final ChargeTypeNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionCodes.GENERIC_CHARGE_TYPE_NOT_FOUND);
        errorResponse.setMessage(ex.getMessage());

        return this.createBodyResponseError(ex, errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> processChargeMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionCodes.GENERIC_BAD_REQUEST_EXCEPTION);
        errorResponse.setMessages(getFieldErrors(ex.getFieldErrors()));

        return this.createBodyResponseError(ex, errorResponse, HttpStatus.BAD_REQUEST);
    }

    private static List<String> getErrorsMessage(List<ObjectError> objectError) {
        return objectError
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }

    private static List<String> getFieldErrors(List<FieldError> fieldObject) {
        return fieldObject
                .stream()
                .map(fieldError -> String.format("[%s] %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    private ResponseEntity<ErrorResponse> createBodyResponseError(Exception exception, final ErrorResponse error,
                                                                  HttpStatus httpStatus) {
        if (this.printStackTrace)
            error.setTrace(ExceptionUtils.getStackTrace(exception));

        return ResponseEntity.status(httpStatus).body(error);
    }
}
