package com.habittracker.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    // Generics T -> Cualquier tipo.
    void save(T entity);
    Optional<T> findById(String id);
    List<T> findAll();
    void delete(String id);
    boolean exists(String id);
}
