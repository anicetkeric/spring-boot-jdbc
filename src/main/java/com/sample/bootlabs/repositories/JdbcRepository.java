package com.sample.bootlabs.repositories;

import java.util.List;
import java.util.Optional;

public interface JdbcRepository<T> {

    T save(T table);

    T update(T table, Long id);

    List<T> findAll();

    Optional<T> findById(Long id);

    int deleteById(Long id);
}
