package com.example.project.controller;

import com.example.project.dao.UserDAO;
import com.example.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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

}