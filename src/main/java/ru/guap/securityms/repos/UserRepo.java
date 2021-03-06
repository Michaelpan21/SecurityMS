package ru.guap.securityms.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.guap.securityms.domain.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
