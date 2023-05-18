package company.controller;

import company.exps.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler({AppBadRequestException.class, ItemNotFoundException.class,
            MethodNotAllowedException.class, WrongException.class, PasswordWrongExeption.class})
    public ResponseEntity<String> handleException(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
