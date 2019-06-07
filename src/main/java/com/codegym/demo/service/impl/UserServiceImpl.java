package com.codegym.demo.service.impl;

import com.codegym.demo.model.User;
import com.codegym.demo.repository.UserRepository;
import com.codegym.demo.service.UserService;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public void delete(User user) {
        userRepository.save(user);

    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
