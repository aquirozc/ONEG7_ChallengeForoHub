package com.aquirozc.forohub.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aquirozc.forohub.data.Login;

public interface LoginRepository extends JpaRepository<Login, String> {
    
    
}
