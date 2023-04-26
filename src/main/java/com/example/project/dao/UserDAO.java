package com.example.project.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, UUID> {

    public List<User> findByNicknameLike(String name);

}