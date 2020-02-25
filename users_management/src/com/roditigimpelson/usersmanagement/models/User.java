package com.roditigimpelson.usersmanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user model implementation
 * Describes the user table in the DB
 * @author Shoham Roditi
 * @author Sveta Gimpelson
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name="username")
    private String username;

    @Column(name="firstname")
    private String firstname;

    @Column(name ="lastname")
    private String lastname;

    @Column(name="password")
    private String password;

    public User(){}

    /**
     * Create a new user
     * @param username
     * @param firstName
     * @param lastName
     * @param password
     */
    public User(String username, String firstName, String lastName, String password){
        setUsername(username);
        setFirstname(firstName);
        setLastname(lastName);
        setPassword(password);
    }

    /**
     * getter and setter
     */
    public String getUsername(){
        return username;
    }

    public void setUsername(String username){ this.username = username; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}