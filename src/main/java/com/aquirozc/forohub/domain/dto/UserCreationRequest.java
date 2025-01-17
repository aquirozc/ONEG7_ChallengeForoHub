package com.aquirozc.forohub.domain.dto;

import com.aquirozc.forohub.config.EncoderSingleton;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserCreationRequest {

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe ser válido")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "El nombre no puede estar vacío")
    @JsonProperty("username")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacia")
    private String password;

    @JsonProperty("password")
    public void setPassword(@NotBlank String password) {
        if(password == null || password.trim().isBlank()){
            throw new IllegalArgumentException("La contraseña no puede estar vacia");
        }
        this.password = EncoderSingleton.getInstance().encode(password);
    }
    
}
