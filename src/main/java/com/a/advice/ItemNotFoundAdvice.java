package com.a.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.a.exception.ItemNotFoundException;

@ControllerAdvice
public class ItemNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(ItemNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String employeeNotFoundHandler(ItemNotFoundException ex) {
    return ex.getMessage();
  }
}