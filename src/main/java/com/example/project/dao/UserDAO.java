package com.example.project.dao;

import java.util.List;

import com.example.project.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {

    public List<User> findByNicknameLike(String name);

    public List<User> findByNumberOfMeetings(int date);

}
