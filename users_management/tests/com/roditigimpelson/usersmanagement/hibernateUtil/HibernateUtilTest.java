package com.roditigimpelson.usersmanagement.hibernateUtil;

import org.hibernate.SessionFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class HibernateUtilTest {

    @Test
    public void getSessionFactory() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    }
}