package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.common.exeption.api.ItemCanNotEmptyException;
import com.example.springsecurityjwt.model.entities.User;
import com.example.springsecurityjwt.service.base.RestService;

public interface UserServiceI extends RestService<User, Long> {
}
