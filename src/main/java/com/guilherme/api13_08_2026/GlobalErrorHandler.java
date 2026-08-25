package com.guilherme.api13_08_2026;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErroResponseDTO> tratarUsuarioNaoEncontrado(
            UsuarioNaoEncontradoException exception,
            HttpServletRequest request
    ) {
        ErroResponseDTO erro = new ErroResponseDTO(
                LocalDateTime.now(),
                404,
                "Not Found",
                exception.getMessage(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity
                .status(404)
                .body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponseDTO> tratarErroValidacao(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        Map<String, String> camposInvalidos = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(erroCampo -> {
                    camposInvalidos.put(
                            erroCampo.getField(),
                            erroCampo.getDefaultMessage()
                    );
                });

        ErroResponseDTO erro = new ErroResponseDTO(
                LocalDateTime.now(),
                400,
                "Bad Request",
                "Existem campos inválidos",
                request.getRequestURI(),
                camposInvalidos
        );

        return ResponseEntity
                .badRequest()
                .body(erro);
    }

    @ExceptionHandler(PokemonNaoEncontradoException.class)
    public ResponseEntity<ErroResponseDTO> tratarPokemonNaoEncontrado(
            PokemonNaoEncontradoException exception,
            HttpServletRequest request
    ) {
        ErroResponseDTO erro = new ErroResponseDTO(
                LocalDateTime.now(),
                404,
                "Not Found",
                exception.getMessage(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity
                .status(404)
                .body(erro);
    }

    @ExceptionHandler(LimiteDePokemonsException.class)
    public ResponseEntity<ErroResponseDTO> tratarLimiteDePokemons(
            LimiteDePokemonsException exception,
            HttpServletRequest request
    ) {
        ErroResponseDTO erro = new ErroResponseDTO(
                LocalDateTime.now(),
                409,
                "Conflict",
                exception.getMessage(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity
                .status(409)
                .body(erro);
    }
}