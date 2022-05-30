package com.revature.mindlight.dao;

import com.revature.mindlight.models.Items;
import com.revature.mindlight.models.Order;
import com.revature.mindlight.models.User;

import java.util.List;

// typecasting any object to be able to use single operation with <T>
public interface CrudDAO<T> {
    void save(T obj);

    void update(T obj);

    void delete(String id);

    T getById(String id);

    List<T> getAll();
}
