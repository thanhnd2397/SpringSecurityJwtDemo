package com.example.springsecurityjwt.service.base;

import com.example.springsecurityjwt.repository.UserRepository;
import com.example.springsecurityjwt.repository.base.RestDao;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class RestServiceImpl<T, ID extends Serializable> implements RestService<T, ID> {

    protected RestDao<T, ID> restDao;

    public RestServiceImpl(RestDao<T, ID> restDao) {
        this.restDao = restDao;
    }

    @Override
    public boolean existsById(ID id) {
        return restDao.existsById(id);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return restDao.exists(example);
    }

    @Override
    public <S extends T, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public long count() {
        return restDao.count();
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return restDao.count(example);
    }

    @Override
    public List<T> findAll() {
        return restDao.findAll();
    }

    @Override
    public List<T> findAll(@SuppressWarnings("exports") Sort sort) {
        return restDao.findAll(sort);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return restDao.findAll(example);
    }

    @Override
    public Page<T> findAll(@SuppressWarnings("exports") Pageable pageable) {
        return restDao.findAll(pageable);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, @SuppressWarnings("exports") Pageable pageable) {
        return restDao.findAll(example, pageable);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, @SuppressWarnings("exports") Sort sort) {
        return restDao.findAll(example, sort);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return restDao.findAllById(ids);
    }

    @Override
    public Optional<T> findById(ID id) {
        return restDao.findById(id);
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return restDao.findOne(example);
    }

    @Override
    public T getOne(ID id) {
        return restDao.getOne(id);
    }

    @Override
    public T getById(ID id) {
        return null;
    }

    @Override
    public T getReferenceById(ID id) {
        return null;
    }

    @Override
    public void delete(T entity) {
        restDao.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {

    }

    @Override
    public void deleteById(ID id) {
        restDao.deleteById(id);
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        restDao.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<T> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<ID> ids) {

    }

    @Override
    public void deleteAllInBatch() {
        restDao.deleteAllInBatch();
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        restDao.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        restDao.deleteAll();
    }

    @Override
    public <S extends T> S save(S entity) {
        return restDao.save(entity);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return restDao.saveAll(entities);
    }

    @Override
    public void flush() {
        restDao.flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return restDao.saveAndFlush(entity);
    }

    @Override
    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }
}

