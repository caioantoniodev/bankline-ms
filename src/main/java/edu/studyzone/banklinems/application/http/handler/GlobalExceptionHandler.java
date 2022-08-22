package edu.studyzone.banklinems.application.http.handler;

import edu.studyzone.banklinems.application.dto.ErrorResponse;
import edu.studyzone.banklinems.infra.exception.ExceptionCodes;
import edu.studyzone.banklinems.infra.exception.InsufficientBalanceException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${exception.trace:false}")
    private boolean printStackTrace;

    @ExceptionHandler(InsufficientBalanceException.class)
    protected ResponseEntity<ErrorResponse> processBadRequestException(final InsufficientBalanceException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionCodes.GENERIC_INSUFFICIENT_BALANCE_EXCEPTION);
        errorResponse.setMessage(ex.getMessage());

        return this.createBodyResponseError(ex, errorResponse, HttpStatus.CONFLICT);
    }

    private ResponseEntity<ErrorResponse> createBodyResponseError(Exception exception, final ErrorResponse error,
                                                                  HttpStatus httpStatus) {

        if (this.printStackTrace) {
            error.setTrace(ExceptionUtils.getStackTrace(exception));
        }
        return ResponseEntity.status(httpStatus).body(error);
    }
}
