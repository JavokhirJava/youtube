package com.controller;

import com.exps.AppBadRequestException;
import com.exps.ItemNotFoundException;
import com.exps.MethodNotAllowedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler({AppBadRequestException.class, ItemNotFoundException.class, MethodNotAllowedException.class})
    public ResponseEntity<String> handleException(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
