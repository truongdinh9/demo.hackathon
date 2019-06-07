package com.codegym.demo.controller;

import com.codegym.demo.model.Category;
import com.codegym.demo.model.User;
import com.codegym.demo.service.CategoryService;
import com.codegym.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping ("api/users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> showAll(){
        List<User> users = (List<User>) userService.findAll();
        if (users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> showById(@PathVariable("id") int id ) {
        User user=userService.findById(id);
        if (user==null){return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody User user ){
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        User user =userService.findById(id);
        if (user==null){return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;}
        userService.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
