package com.codegym.demo.service;


import com.codegym.demo.model.Category;

public interface CategoryService {
    Iterable<Category> findAll();
    Category findById(int id);
    void  save(Category category);
    void delete(Category category);

}
