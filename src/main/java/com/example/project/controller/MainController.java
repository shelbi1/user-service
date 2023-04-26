package com.example.project.controller;

import com.example.project.exception.ErrorMessage;
import com.example.project.exception.UserNotFoundException;
import com.example.project.dao.UserDAO;
import com.example.project.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/users")
public class MainController {

    @Autowired
    private UserDAO userDAO;

    @ResponseBody
    @RequestMapping("/users")
    public String index() {
        Iterable<User> all = userDAO.findAll();

        StringBuilder sb = new StringBuilder();

        all.forEach(p -> sb.append(p.getNickname() + "<br>"));

        return sb.toString();
    }


    @PostMapping("/")
    public ResponseEntity<User> saveUser(@ModelAttribute("user") User user) {
        userDAO.save(user);
        return ResponseEntity.created(URI.create("/" + user.getId())).body(user);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id) {

        User user = userDAO.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " is not found"));
        return ResponseEntity.ok(user);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") UUID id,
                                         @ModelAttribute("user") User updatedUser) {

        User user = userDAO.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " is not found"));
        user.setId(updatedUser.getId());
        user.setNickname(updatedUser.getNickname());
        user.setNumberOfMeetings(updatedUser.getNumberOfMeetings());
        userDAO.save(user);
        return ResponseEntity.ok(user);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(@NotNull UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") UUID id) {
        try {
            userDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        }
    }

}