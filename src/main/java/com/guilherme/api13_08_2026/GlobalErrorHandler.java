package com.guilherme.api13_08_2026;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<String> tratarUsuarioNaoEncontrado(
            UsuarioNaoEncontradoException exception) {

        return ResponseEntity
                .status(404)
                .body(exception.getMessage());
    }
}