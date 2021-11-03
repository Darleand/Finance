package com.app.finance.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.zip.DataFormatException;

@ControllerAdvice
public class ControllerAdviceException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MyEntityNotFoundException.class)
    protected ResponseEntity<Response> handlerNotFound(MyEntityNotFoundException ex) {
        Response response = new Response("Данных за этот период времени нет!", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFormatTypeCurrencyException.class)
    protected ResponseEntity<Response> handlerNotFound(NotFormatTypeCurrencyException ex) {
        Response response = new Response("Не верно введен код валюты. Формат ввода '###', # - цифра.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataFormatException.class)
    protected ResponseEntity<Response> handlerNotFound(DataFormatException ex) {
        Response response = new Response("Не верно введена дата. Формат ввода 'yyyy-mm-dd'.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<Response> handlerNotFound(HttpClientErrorException ex) {
        Response response = new Response("Не верно введена дата. Формат ввода 'yyyy-mm-dd'.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
