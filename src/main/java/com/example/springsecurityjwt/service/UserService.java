package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.common.exeption.api.ItemCanNotEmptyException;
import com.example.springsecurityjwt.model.security.CustomUserDetails;
import com.example.springsecurityjwt.model.entities.User;
import com.example.springsecurityjwt.repository.UserRepository;
import com.example.springsecurityjwt.service.base.RestServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserService extends RestServiceImpl<User, Long> implements UserDetailsService, UserServiceI  {

    private final UserRepository dao;

    public UserService(UserRepository dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = dao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }
}
