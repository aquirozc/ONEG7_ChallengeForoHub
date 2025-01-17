package com.aquirozc.forohub.domain.dto;

import java.sql.Date;

import com.aquirozc.forohub.domain.model.Topic;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TopicGenericResponse {

    public TopicGenericResponse(Topic topic){
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.date = topic.getDate();
        this.isActive = topic.isActive();
        this.course = topic.getCourse();
        this.userID = topic.getUser().getId();
    }

    private long id;

    private String title;

    private String message;

    @JsonProperty("creation_date")
    private Date date;

    @JsonProperty("is_active")
    private boolean isActive;

    private String course;

    @JsonProperty("user_id")
    private long userID;
    
}
