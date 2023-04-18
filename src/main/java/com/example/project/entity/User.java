package com.example.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Nickname", length = 64, nullable = false)
    private String nickname;

    @Column(name = "Number_Of_Meetings", nullable = false)
    private int numberOfMeetings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getNumberOfMeetings() {
        return numberOfMeetings;
    }

    public void setNumberOfMeetings(int numberOfMeetings) {
        this.numberOfMeetings = numberOfMeetings;
    }
}
