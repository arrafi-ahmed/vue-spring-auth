package com.example.vuespringauth.model;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String name;
    private OffsetDateTime createTime;
    private Role role;
//    @Transient
//    private String token;

    public User() {
    }

    public User(Long id, String username, String password, String name, OffsetDateTime createTime, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.createTime = createTime;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(OffsetDateTime createTime) {
        this.createTime = createTime;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", role=" + role +
                '}';
    }
}
