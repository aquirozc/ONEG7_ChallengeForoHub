package com.aquirozc.forohub.infra;

public class EmailAlreadyTakenException extends RuntimeException {

    public EmailAlreadyTakenException(String email) {
        super(email);
    }
    
}
