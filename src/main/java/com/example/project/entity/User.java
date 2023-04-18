package com.example.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
