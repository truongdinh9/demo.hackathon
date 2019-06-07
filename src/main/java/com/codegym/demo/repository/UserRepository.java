package com.codegym.demo.repository;

import com.codegym.demo.model.Category;
import com.codegym.demo.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User,Integer> {
    Iterable<User> findAllByCategory(Category category);
}
