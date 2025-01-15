package com.aquirozc.forohub.data;

import com.aquirozc.forohub.config.EncoderSingleton;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USUARIOS", schema = "FOROHUB")
public class User{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "USUARIO_ID")
    private long id;

    @Column(name = "CORREO", nullable = false, unique = true)
    private String email;

    @Column(name = "CONTRASEÑA", nullable = false)
    private String password;

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = EncoderSingleton.getInstance().encode(password);
    }
    
}
    

