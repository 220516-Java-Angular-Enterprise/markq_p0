package com.revature.qmart.dao;

import java.util.List;

// typecasting any object to be able to use single operation with <T>
public interface CrudDAO<T> {
    void save(T obj);

    void update(T obj);

    void delete(String id);

    T getById(String id);

    List<T> getAll();
}
