package com.app.finance.web;

import com.app.finance.entity.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import java.util.zip.DataFormatException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    @ExceptionHandler(MyEntityNotFoundException.class)
    protected ResponseEntity<ResponseException> handlerNotFound(MyEntityNotFoundException ex) {
        ResponseException responseException = new ResponseException("Данных за этот период времени нет!", ex.getMessage());
        return new ResponseEntity<>(responseException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFormatTypeCurrencyException.class)
    protected ResponseEntity<ResponseException> handlerNotFound(NotFormatTypeCurrencyException ex) {
        ResponseException responseException = new ResponseException("Не верно введен код валюты. Формат ввода '###', # - цифра.", ex.getMessage());
        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataFormatException.class)
    protected ResponseEntity<ResponseException> handlerNotFound(DataFormatException ex) {
        ResponseException responseException = new ResponseException("Не верно введена дата. Формат ввода 'yyyy-mm-dd'.", ex.getMessage());
        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<ResponseException> handlerNotFound(HttpClientErrorException ex) {
        ResponseException responseException = new ResponseException("Не верно введена дата. Формат ввода 'yyyy-mm-dd'.", ex.getMessage());
        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
    }
}
