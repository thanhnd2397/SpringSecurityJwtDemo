package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.model.entities.Item;
import com.example.springsecurityjwt.service.base.RestServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class ItemServiceImpl extends RestServiceImpl<Item, Integer> implements ItemService{
}
