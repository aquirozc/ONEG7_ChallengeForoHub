package com.aquirozc.forohub.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserLoginRequest {

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe ser válido")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @JsonProperty("password")
    private String password;

}
