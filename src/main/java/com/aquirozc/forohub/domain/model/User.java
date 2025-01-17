package com.aquirozc.forohub.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "USUARIOS", schema = "FOROHUB")
public class User{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "USUARIO_ID")
    private long id;

    @Column(name = "NOMBRE", nullable = false)
    private String name;

    @Column(name = "CORREO", nullable = false, unique = true)
    private String email;

    @Column(name = "CONTRASEÑA", nullable = false)
    private String password;
    
}
    

