package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.model.CustomUserDetails;
import com.example.springsecurityjwt.model.User;
import com.example.springsecurityjwt.repository.UseraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UseraRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserByUserId(Long id) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findById(id).get();
        return new CustomUserDetails(user);
    }
}
