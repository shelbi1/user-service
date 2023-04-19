package com.example.project.dao;

import java.util.List;
import java.util.Optional;

import com.example.project.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDAOInterface extends CrudRepository<User, Long> {

    public List<User> findByNickname(String name);

    public Optional<User> get(long id);

    public List<User> getAll();

    public void save(User user);

    public User getUserById(long id);

    public void update(User user);

    public void deleteById(long id);
}
