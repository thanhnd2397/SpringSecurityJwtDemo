package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.common.exeption.APIException;
import com.example.springsecurityjwt.common.exeption.api.ItemCanNotEmptyException;
import com.example.springsecurityjwt.config.JwtTokenProvider;
import com.example.springsecurityjwt.dto.LoginRequest;
import com.example.springsecurityjwt.dto.LoginResponse;
import com.example.springsecurityjwt.dto.Message;
import com.example.springsecurityjwt.helper.MessageConst;
import com.example.springsecurityjwt.model.CustomUserDetails;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1")
public class Controller extends BaseController{
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping("/login")
    public Object authenticateUser(@RequestBody LoginRequest loginRequest, Locale locale) throws APIException {
        if (Strings.isNullOrEmpty(loginRequest.getUsername()) || Strings.isNullOrEmpty(loginRequest.getPassword())) {
            throw new ItemCanNotEmptyException("empty");
        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            redisTemplate.opsForValue().set("user", loginRequest);
            System.out.println(locale);
            return ResponseEntity.ok(resFactory.ok(MessageConst.I0001, locale, new LoginResponse(jwt)));
        }catch (Exception e ){
            throw new ItemCanNotEmptyException("...........");
        }
    }

    @GetMapping("/message")
    public ResponseEntity<Message> randomStuff() {
        System.out.println(redisTemplate.opsForValue().get("user"));
        return ResponseEntity.ok(new Message("TEST___________________________"));
    }

}
