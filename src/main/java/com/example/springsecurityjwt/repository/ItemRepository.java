package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.model.entities.Item;
import com.example.springsecurityjwt.repository.base.RestDao;

public interface ItemRepository extends RestDao<Item, Long> {
}
