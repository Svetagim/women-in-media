package com.roditigimpelson.usersmanagement.models;

import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @org.junit.Before
    public void setUp() throws Exception {
        user = new User("uName","first", "last", "pass");
    }

    @org.junit.After
    public void tearDown() throws Exception {
        user = null;
    }

    @org.junit.Test
    public void getUsername() {
        String expected = "uName";
        String actual = user.getUsername();
        assertSame(expected, actual);
    }

    @org.junit.Test
    public void setUsername() {
        user.setUsername("uName1");
        String expected = "uName1";
        String actual = user.getUsername();
        assertSame(expected,actual);
    }

    @org.junit.Test
    public void getFirstname() {
        String expected = "first";
        String actual = user.getFirstname();
        assertSame(expected, actual);

    }

    @org.junit.Test
    public void setFirstname() {
        user.setFirstname("first1");
        String expected = "first1";
        String actual = user.getFirstname();
        assertSame(expected,actual);
    }

    @org.junit.Test
    public void getLastname() {
        String expected = "last";
        String actual = user.getLastname();
        assertSame(expected, actual);
    }

    @org.junit.Test
    public void setLastname() {
        user.setLastname("last1");
        String expected = "last1";
        String actual = user.getLastname();
        assertSame(expected,actual);
    }

    @org.junit.Test
    public void getPassword() {
        String expected = "pass";
        String actual = user.getPassword();
        assertSame(expected, actual);
    }

    @org.junit.Test
    public void setPassword() {
        user.setPassword("pass1");
        String expected = "pass1";
        String actual = user.getPassword();
        assertSame(expected,actual);
    }
}