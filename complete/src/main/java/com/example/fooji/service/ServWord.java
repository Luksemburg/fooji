package com.example.fooji.service;

import com.example.fooji.entity.EntWord;
import com.example.fooji.repo.RepWord;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ServWord implements RepWord{

    /*
    private final RepWord repository;

    public ServWord(RepWord repository) {
        this.repository = repository;
    }
    */

    long tempId = 5;

    public Optional<EntWord> getRandomWord(){
        Optional<EntWord> res = Optional.of(new EntWord());
        try {
            res = this.findById(tempId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends EntWord> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends EntWord> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<EntWord> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public EntWord getOne(Long aLong) {
        return null;
    }

    @Override
    public EntWord getById(Long aLong) {
        return null;
    }

    @Override
    public EntWord getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends EntWord> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends EntWord> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends EntWord> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends EntWord> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends EntWord> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends EntWord> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends EntWord, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends EntWord> S save(S entity) {
        return null;
    }

    @Override
    public <S extends EntWord> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<EntWord> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<EntWord> findAll() {
        return List.of();
    }

    @Override
    public List<EntWord> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(EntWord entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends EntWord> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<EntWord> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<EntWord> findAll(Pageable pageable) {
        return null;
    }
}
