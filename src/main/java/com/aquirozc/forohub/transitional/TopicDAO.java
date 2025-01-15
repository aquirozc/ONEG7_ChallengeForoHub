package com.aquirozc.forohub.transitional;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TopicDAO {

    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    @NotBlank(message = "El mensaje no puede estar vacío")
    private String message;

    @NotBlank(message = "El curso no puede estar vacío")
    private String course;
    
}
