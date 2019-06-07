package com.codegym.demo.service;

import com.codegym.demo.model.Category;
import com.codegym.demo.model.Question;


public interface QuestionService {
    Iterable<Question> findAll();
    Question findById(int id);
    void delete(int id);
    void save(Question question);
    Iterable<Question> findAllByCategory(Category category);
    Iterable<Question> getTenQuestions(Category category);
}
