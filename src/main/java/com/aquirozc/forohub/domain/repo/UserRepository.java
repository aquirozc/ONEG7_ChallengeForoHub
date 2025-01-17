package com.aquirozc.forohub.domain.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aquirozc.forohub.domain.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

}
