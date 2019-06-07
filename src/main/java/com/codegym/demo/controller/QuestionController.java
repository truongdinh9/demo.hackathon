package com.codegym.demo.controller;

import com.codegym.demo.model.Category;
import com.codegym.demo.model.Question;
import com.codegym.demo.service.CategoryService;
import com.codegym.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping ("api/questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Question>> showAll(){
        List<Question> questions = (List<Question>) questionService.findAll();
        if (questions.isEmpty()){
            return new ResponseEntity<List<Question>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Question>>(questions,HttpStatus.OK);
    }
    @GetMapping ("/ten/{categoryId}")
    public ResponseEntity<List<Question>> getTenQuestionByCategory(@PathVariable("categoryId") int id ){
        Category category = categoryService.findById(id);
        List<Question> questions = (List<Question>) questionService.getTenQuestions(category);
        if (questions.isEmpty()){
            return new ResponseEntity<List<Question>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Question>>(questions,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> showById(@PathVariable("id") int id ) {
        Question question=questionService.findById(id);
        if (question==null){return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        return new ResponseEntity<Question>(question,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Question question ){
        questionService.save(question);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> edit(@PathVariable("id")int id, @RequestBody Question newQuestion){
        Question currentQuestion=questionService.findById(id);
        if (currentQuestion==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentQuestion.setContent(newQuestion.getContent());
        currentQuestion.setOptionA(newQuestion.getOptionA());
        currentQuestion.setOptionB(newQuestion.getOptionB());
        currentQuestion.setOptionC(newQuestion.getOptionC());
        currentQuestion.setOptionD(newQuestion.getOptionD());
        currentQuestion.setResult(newQuestion.getResult());

        questionService.save(currentQuestion);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        Question question =questionService.findById(id);
        if (question==null){return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;}
        questionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
