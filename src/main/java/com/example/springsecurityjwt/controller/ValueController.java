package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.common.exeption.APIException;
import com.example.springsecurityjwt.common.exeption.api.ItemCanNotEmptyException;
import com.example.springsecurityjwt.helper.CodeConst;
import com.example.springsecurityjwt.helper.MessageConst;
import com.example.springsecurityjwt.model.entities.Value;
import com.example.springsecurityjwt.model.request.ValueRequest;
import com.example.springsecurityjwt.model.response.LoginResponse;
import com.example.springsecurityjwt.model.response.common.ResponseFactory;
import com.example.springsecurityjwt.service.ItemService;
import com.example.springsecurityjwt.service.ValueService;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class ValueController extends BaseController{

    private final ValueService service;
    private final ItemService iService;

    public ValueController(ResponseFactory resFactory, ValueService service, ItemService iService) {
        super(resFactory);
        this.service = service;
        this.iService = iService;
    }

    @PostMapping("/value/upload")
    public Object uploadValue(@ModelAttribute ValueRequest valueRequest, HttpServletRequest request) throws APIException, IOException {
        if (valueRequest.getImage().isEmpty() & Strings.isNullOrEmpty(valueRequest.getText())) {
            throw new ItemCanNotEmptyException("Value Empty");
        }
        try {
            Value value = new Value();
            if (valueRequest.getImage() != null) {
                String imgLink = service.uploadFile(valueRequest.getImage(), valueRequest.getImage().getOriginalFilename());
                value.setItem(iService.getById(valueRequest.getItemId()));
                value.setText(null);
                value.setImageName(imgLink);
                value.setExt(Files.getFileExtension(Objects.requireNonNull(valueRequest.getImage().getOriginalFilename())));
                service.save(value);
            }
            return ResponseEntity.ok(resFactory.ok("Successfully Uploaded !"));
        } catch (Exception e ) {
            return ResponseEntity.status(CodeConst.ERROR).body(resFactory.fail("Unsuccessfully Uploaded!"));
        }
    }
}
