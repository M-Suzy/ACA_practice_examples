package model;

import annotations.Column;
import annotations.Table;


@Table(name = "users")
public class User {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    private int age;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

}
