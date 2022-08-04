package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.common.exeption.APIException;
import com.example.springsecurityjwt.common.exeption.api.ItemCanNotEmptyException;
import com.example.springsecurityjwt.common.exeption.api.LoginException;
import com.example.springsecurityjwt.config.JwtTokenProvider;
import com.example.springsecurityjwt.helper.CodeConst;
import com.example.springsecurityjwt.helper.RedisKeyHelper;
import com.example.springsecurityjwt.model.request.LoginRequest;
import com.example.springsecurityjwt.model.response.LoginResponse;
import com.example.springsecurityjwt.model.response.Message;
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

import java.util.*;

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
            redisTemplate.opsForValue().set(RedisKeyHelper.buildApiKey("data"), data);
            return ResponseEntity.ok(resFactory.ok(MessageConst.I0001, locale, new LoginResponse(jwt)));
        }catch (Exception e){
            return ResponseEntity.status(CodeConst.UNAUTHORIZED).body(resFactory.fail(locale, "Wrong username or password"));
        }
    }

    @GetMapping("/message")
    public ResponseEntity<Message> randomStuff() {
        System.out.println(redisTemplate.opsForValue().get("mp_Key:data"));
        return ResponseEntity.ok(new Message("TEST___________________________"));
    }

}
