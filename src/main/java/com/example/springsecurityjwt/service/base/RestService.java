package com.example.springsecurityjwt.service.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface RestService<T, ID extends Serializable> extends JpaRepository<T, ID> {

}
