package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UseraRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
