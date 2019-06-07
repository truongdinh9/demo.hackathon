package com.codegym.demo.repository;


import com.codegym.demo.model.Category;
import com.codegym.demo.model.Question;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionReposity extends PagingAndSortingRepository<Question,Integer> {
    Iterable<Question> findAllByCategory(Category category);
    Iterable<Question> findAllByContentContaining(String s);
}
