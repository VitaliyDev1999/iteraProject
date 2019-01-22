package project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import project.exception.EqualNumbersForRangeException;
import project.exception.ErrorResponseDto;
import project.exception.NumberAreNotSuitableForRouletteException;
import project.exception.SecondRangeIsOutOfFirstBoundException;

@ControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(EqualNumbersForRangeException.class)
    public ResponseEntity<ErrorResponseDto> handleExceptionOfEqualNumbersException(EqualNumbersForRangeException exc) {
        return getResponseBadRequest(exc);
    }

    @ExceptionHandler(NumberAreNotSuitableForRouletteException.class)
    public ResponseEntity<ErrorResponseDto> handleExceptionOfEqualNumbersException(NumberAreNotSuitableForRouletteException exc) {
        return getResponseBadRequest(exc);
    }

    @ExceptionHandler(SecondRangeIsOutOfFirstBoundException.class)
    public ResponseEntity<ErrorResponseDto> handleExceptionOfEqualNumbersException(SecondRangeIsOutOfFirstBoundException exc) {
        return getResponseBadRequest(exc);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleExceptionOfEqualNumbersException(Exception exc) {
        return getResponseBadRequest(exc);
    }

    private ResponseEntity<ErrorResponseDto> getResponseBadRequest(Exception exc){
        ErrorResponseDto error = new ErrorResponseDto();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        error.setException(exc.getClass().getName());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
