package com.aquirozc.forohub.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TopicCreationRequest {

    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    @NotBlank(message = "El mensaje no puede estar vacío")
    private String message;

    @NotBlank(message = "El curso no puede estar vacío")
    private String course;
    
}
