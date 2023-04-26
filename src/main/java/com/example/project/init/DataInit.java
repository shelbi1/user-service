package com.example.project.init;

import com.example.project.dao.UserDAO;
import com.example.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements ApplicationRunner {

    private UserDAO userDAO;

    @Autowired
    public DataInit(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = userDAO.count();

        if (count == 0) {
            User u1 = new User();
            u1.setNickname("Sofia");
            int numberOfMeetings1 = 17;
            u1.setNumberOfMeetings(numberOfMeetings1);
            //
            User u2 = new User();
            u2.setNickname("Sonya");
            int numberOfMeetings2 = 21;
            u2.setNumberOfMeetings(numberOfMeetings2);

            userDAO.save(u1);
            userDAO.save(u2);
        }
    }

}
