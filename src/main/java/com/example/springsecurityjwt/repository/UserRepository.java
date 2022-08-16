package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.model.entities.User;
import com.example.springsecurityjwt.repository.base.RestDao;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends RestDao<User, Long> {
    User findByUsername(String username);

}
