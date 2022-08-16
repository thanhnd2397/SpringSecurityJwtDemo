package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.common.exeption.APIException;
import com.example.springsecurityjwt.common.exeption.api.ItemCanNotEmptyException;
import com.example.springsecurityjwt.config.JwtTokenProvider;
import com.example.springsecurityjwt.helper.CodeConst;
import com.example.springsecurityjwt.model.entities.User;
import com.example.springsecurityjwt.model.request.CreateUserRequest;
import com.example.springsecurityjwt.model.request.LoginRequest;
import com.example.springsecurityjwt.model.response.LoginResponse;
import com.example.springsecurityjwt.model.response.Message;
import com.example.springsecurityjwt.helper.MessageConst;
import com.example.springsecurityjwt.model.response.common.ResponseFactory;
import com.example.springsecurityjwt.model.security.CustomUserDetails;
import com.example.springsecurityjwt.service.PdfService;
import com.example.springsecurityjwt.service.UserServiceI;
import com.google.common.base.Strings;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class Controller extends BaseController{
    final
    AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    @Autowired
    private RedisTemplate redisTemplate;

    private final PdfService pdfService;

    private final UserServiceI uService;
    public Controller(ResponseFactory resFactory, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, PdfService pdfService, UserServiceI uService) {
        super(resFactory);
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.pdfService = pdfService;
        this.uService = uService;
    }

    @PostMapping("/login")
    public Object authenticateUser(@RequestBody LoginRequest loginRequest, Locale locale) throws APIException {
        if (Strings.isNullOrEmpty(loginRequest.getUsername()) || Strings.isNullOrEmpty(loginRequest.getPassword())) {
            throw new ItemCanNotEmptyException("Enter missing username or password");
        }
        List<LoginRequest> data;
        LoginRequest u1 = new LoginRequest("a1111","a22222");
        LoginRequest u2 = new LoginRequest("a1111","a22222");
        data = Arrays.asList(u1, u2);
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
    @PostMapping("/message")
    public Object randomStuff() {
        System.out.println(redisTemplate.opsForValue().get("data"));
        redisTemplate.delete("mp_Key:data");
        return ResponseEntity.ok(new Message("TEST___________________________"));
    }

    @PostMapping("/user")
    public Object createOrUpdateUser(@RequestBody CreateUserRequest createUserRequest) {
        User user = new User();
        if (createUserRequest.getId() != null) {
            user.setId(createUserRequest.getId());
        } else {
            user.setId(null);
        }
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(createUserRequest.getPassword());
        return ResponseEntity.ok(resFactory.ok(uService.save(user), "Create user successfully "));
    }

    @GetMapping("/export")
    public Object exportPdf(HttpServletResponse response) throws Exception {
        File file = pdfService.exportPdfFile();
        byte[] zipBytes = FileUtils.readFileToByteArray(file);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-Length", String.valueOf(zipBytes.length));
        response.setHeader("Content-Disposition", "attachment; filename=" + "example.pdf");
        response.getOutputStream().write(zipBytes);
        return ResponseEntity.ok(resFactory.ok("ok"));
    }

}
