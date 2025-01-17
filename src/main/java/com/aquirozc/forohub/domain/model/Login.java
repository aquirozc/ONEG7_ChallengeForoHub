package com.aquirozc.forohub.domain.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "SESIONES" , schema = "FOROHUB")
public class Login {

    @Id
    @Column(name = "SESSION_ID", length = 2048)
    private String token;

    @Column(name = "REVOCADO")
    private boolean revoked;

    @Column(name ="VENCIMIENTO")
    private Date expiration;

    @ManyToOne
    @JoinColumn(name = "USUARIO_FK", nullable = false)
    private User user;
    
}
