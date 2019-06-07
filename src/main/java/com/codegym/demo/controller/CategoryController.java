package com.codegym.demo.controller;

import com.codegym.demo.model.Category;
import com.codegym.demo.service.CategoryService;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping ("api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>> show(){
        List<Category> categories = (List<Category>) categoryService.findAll();
        if (categories.isEmpty()){
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> showById(@PathVariable("id") int id ) {
        Category category=categoryService.findById(id);
        if (category==null){return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        return new ResponseEntity<Category>(category,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Category category ){
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> edit(@PathVariable("id")int id, @RequestBody Category newcategory){
        Category currentCategory=categoryService.findById(id);
        if (currentCategory==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentCategory.setName(newcategory.getName());
        categoryService.save(currentCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        Category category =categoryService.findById(id);
        if (category==null){return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;}
        categoryService.delete(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
