package com.example.project.dao;

import com.example.project.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;


public class UserDAO implements UserDAOInterface {

    private EntityManager entityManager;

    @Override
    public List<User> findByNickname(String nickname){
        return (List<User>) entityManager.find(User.class, nickname);
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM User e");
        return query.getResultList();
    }

    @Override
    public void save(User user) {
        executeInsideTransaction(entityManager -> entityManager.persist(user));
    }

    @Override
    public User getUserById(long id){
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User user){
        entityManager.persist(user);
    }

    /*
        @Override
        public void update(User user, String nickname, int numberOfMeetings) {
        user.setNickname(Objects.requireNonNull(nickname, "Name cannot be null"));
        user.setNumberOfMeetings(numberOfMeetings);
        executeInsideTransaction(entityManager -> entityManager.merge(user));
    }
     */

    @Override
    public void deleteById(long id){
        executeInsideTransaction(entityManager -> entityManager.remove(getUserById(id)));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        final EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    /*
    public class UserDAO implements UserDAOInterface {

    public UserDAOInterface userDAO;

    @Override
    public List<User> findByNickname(String name) {
        return null;
    }

    @Override
    public User getUserById(Long id){
        String sql="select * from USERS where id=?";
        return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public int update(User u){
        String sql="update USERS set Nickname='"u.getNickname()+"', Number_Of_Meetings="+u.getNumberOfMeetings()+"' where id="+u.getId()+"";
        return template.update(sql);
    }

    @Override
    public int delete(Long id){
        String sql="delete from USERS where id="+id+"";
        return template.update(sql);
    }
}
*/
    /*
    private List<User> users = new ArrayList<>();

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(users.get((int) id));
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void update(User user, String nickname, int numberOfMeetings) {
        user.setNickname(Objects.requireNonNull(
                nickname, "Nickname cannot be null"));
        user.setNumberOfMeetings(numberOfMeetings);

        users.add(user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public void deleteById(Long id){
*/
}