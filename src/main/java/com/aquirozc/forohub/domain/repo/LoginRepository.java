package com.aquirozc.forohub.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aquirozc.forohub.domain.model.Login;

public interface LoginRepository extends JpaRepository<Login, String> {
    
    
}
