package com.aquirozc.forohub.data;

import java.sql.Date;

import com.aquirozc.forohub.transitional.TopicDAO;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "TEMAS", schema = "FOROHUB", uniqueConstraints = @UniqueConstraint(columnNames = {"TITULO", "CURSO"}))
public class Topic {

    public Topic(TopicDAO topic, User user) {
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.date = new Date(System.currentTimeMillis());
        this.isActive = true;
        this.course = topic.getCourse();
        this.user = user;
    }


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "TEMA_ID")
    private long id;

    @Column(name = "TITULO", nullable = false)
    private String title;

    @Column(name = "DESCRIPCION", nullable = false)
    private String message;

    @Column(name = "FECHA", nullable = false)
    private Date date;

    @Column(name = "ABIERTO", nullable = false)
    private boolean isActive;

    @Column(name = "CURSO", nullable = false)
    private String course;

    @ManyToOne
    @JoinColumn(name = "USUARIO_FK", nullable = false, referencedColumnName = "USUARIO_ID")
    private User user;
    
    @JsonProperty("user")
    private long getUserId() {
        return user.getId();
    }
}
