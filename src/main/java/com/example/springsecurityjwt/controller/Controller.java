package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.common.exeption.APIException;
import com.example.springsecurityjwt.common.exeption.api.ItemCanNotEmptyException;
import com.example.springsecurityjwt.config.JwtTokenProvider;
import com.example.springsecurityjwt.helper.CodeConst;
import com.example.springsecurityjwt.helper.RedisKeyHelper;
import com.example.springsecurityjwt.model.request.LoginRequest;
import com.example.springsecurityjwt.model.response.LoginResponse;
import com.example.springsecurityjwt.model.response.Message;
import com.example.springsecurityjwt.helper.MessageConst;
import com.example.springsecurityjwt.model.response.common.ResponseFactory;
import com.example.springsecurityjwt.model.security.CustomUserDetails;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class Controller extends BaseController{
    final
    AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    private final RedisTemplate redisTemplate;

    public Controller(ResponseFactory resFactory, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, RedisTemplate redisTemplate) {
        super(resFactory);
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/login")
    public Object authenticateUser(@RequestBody LoginRequest loginRequest, Locale locale) throws APIException {
        if (Strings.isNullOrEmpty(loginRequest.getUsername()) || Strings.isNullOrEmpty(loginRequest.getPassword())) {
            throw new ItemCanNotEmptyException("Enter missing username or password");
        }
        List<LoginRequest> data  = new ArrayList<>();
        LoginRequest u1 = new LoginRequest("a1111","a22222");
        LoginRequest u2 = new LoginRequest("a1111","a22222");
        LoginRequest u3 = new LoginRequest("a1111","a22222");
        data = Arrays.asList(u1, u2, u3);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            redisTemplate.opsForValue().set("data", data);
            return ResponseEntity.ok(resFactory.ok(MessageConst.I0001, locale, new LoginResponse(jwt)));
        }catch (Exception e){
            return ResponseEntity.status(CodeConst.UNAUTHORIZED).body(resFactory.fail(locale, "Wrong username or password"));
        }
    }

    @GetMapping("/message")
    public Object randomStuff() {
        System.out.println(redisTemplate.opsForValue().get("data"));
        redisTemplate.delete("mp_Key:data");
        return ResponseEntity.ok(new Message("TEST___________________________"));
    }

}
