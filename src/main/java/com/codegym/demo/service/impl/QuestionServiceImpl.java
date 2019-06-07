package com.codegym.demo.service.impl;

import com.codegym.demo.model.Category;
import com.codegym.demo.model.Question;
import com.codegym.demo.repository.QuestionReposity;
import com.codegym.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionReposity questionReposity;
    @Override
    public Iterable<Question> findAll() {
        return questionReposity.findAll();
    }

    @Override
    public Question findById(int id) {
        return questionReposity.findById(id).get();
    }

    @Override
    public void delete(int id) {
        questionReposity.deleteById(id);

    }

    @Override
    public void save(Question question) {
        questionReposity.save(question);

    }

    @Override
    public Iterable<Question> findAllByCategory(Category category) {
        return questionReposity.findAllByCategory(category);
    }

    @Override
    public ArrayList<Question> getTenQuestions(Category category) {
        ArrayList<Question> listAllQuestByCategory = (ArrayList<Question>) this.findAllByCategory(category);
        int index = (int) Math.round(Math.random()*(listAllQuestByCategory.size()-10));
        ArrayList<Question> tenQuestions = new ArrayList<Question>(10);
        for (int i = index; i < index+10 ; i++) {
            tenQuestions.add(listAllQuestByCategory.get(i));
        }
        return tenQuestions;
    }
}
