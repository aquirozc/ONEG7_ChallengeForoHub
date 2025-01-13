package com.aquirozc.forohub.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aquirozc.forohub.data.User;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

}
