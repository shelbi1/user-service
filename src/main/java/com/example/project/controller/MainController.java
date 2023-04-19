package com.example.project.controller;

import com.example.project.dao.UserDAO;
import com.example.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.sun.tools.javac.util.List.from;

@RestController
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

    @PostMapping(value="/createUser")
    public void createUser(@RequestParam(name = "name", required = false, defaultValue = "defaultName") String nickname, User user) {
        user.setNickname(nickname);
        user.setNumberOfMeetings(0);
        userDAO.save(user);
    }

    @PostMapping(value="/saveUser")
    public String saveUser(@ModelAttribute("user") User u){
        userDAO.save(u);
        return ResponseEntity.ok(u);
        //return "redirect:/viewUser";
    }

    @GetMapping(value="/getUser/{id}")
    public String getUser(Long id){
        User u = userDAO.getUserById(id);
        return "getUserForm";
    }

    @PutMapping(value="/editUser/{id}")
    public String editUser(@PathVariable Long id){
        User user = userDAO.getUserById(id);
        userDAO.update(user);
        return "editUserForm";
    }

    @DeleteMapping(value="/deleteUser/{id}")
    public String delete(@PathVariable Long id){
        userDAO.deleteById(id);
        return "redirect:/viewUser";
    }

}