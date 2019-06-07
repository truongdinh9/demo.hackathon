package com.codegym.demo.service;


import com.codegym.demo.model.User;

public interface UserService {
    void save(User user);
    void delete(User user);
     User findById( int id);
    Iterable<User> findAll();
}
