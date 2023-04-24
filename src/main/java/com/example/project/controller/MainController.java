package com.example.project.controller;

import com.example.project.UserNotFoundException;
import com.example.project.dao.UserDAO;
import com.example.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserDAO userDAO;

    @ResponseBody
    @RequestMapping("/")
    public String index() {
        Iterable<User> all = userDAO.findAll();

        StringBuilder sb = new StringBuilder();

        all.forEach(p -> sb.append(p.getNickname() + "<br>"));

        return sb.toString();
    }


    @PostMapping(value="/save-user")
    public ResponseEntity<User> saveUser(@ModelAttribute("user") User user){
        userDAO.save(user);
        return ResponseEntity.created(URI.create("/" + user.getId())).body(user);
    }

    @GetMapping(value="/user/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("id") UUID id){
        try {
            Optional<User> user = userDAO.findById(id);
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value="/edit-user/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") UUID id,
                                         @ModelAttribute("user") User updatedUser){
        try {
            userDAO.findById(id);
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }

        User user = userDAO.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " is not found"));
        user.setId(updatedUser.getId());
        user.setNickname(updatedUser.getNickname());
        user.setNumberOfMeetings(updatedUser.getNumberOfMeetings());
        userDAO.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value="/delete-user/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") UUID id){
        try {
            userDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

}