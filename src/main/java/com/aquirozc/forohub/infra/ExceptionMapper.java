package com.aquirozc.forohub.infra;

import javax.security.sasl.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleBadCredentials(AuthenticationException e){
        return ResponseEntity.status(401).body("Usuario y/o contraseña incorrectos");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ReadOnlyTopicException.class)
    public ResponseEntity<String> handleReadOnlyTopic(ReadOnlyTopicException e){
        return ResponseEntity.status(403).body("Sin permisos para editar este tema");
    }

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<String> handleTopicNotFound(TopicNotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(TopicAlreadySubmitedException.class)
    public ResponseEntity<String> handleTopicAlreadySubmited(TopicAlreadySubmitedException e){
        return ResponseEntity.badRequest().body("No se admiten post repetidos");
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<String> handleUserAlreadyRegistered(EmailAlreadyTakenException e){
        return ResponseEntity.badRequest().body(String.format("El usuario %s ya esta registrado",e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationNotMeet(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(e.getFieldError().getDefaultMessage());
    }
    
   
}
