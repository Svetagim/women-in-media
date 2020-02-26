package com.roditigimpelson.usersmanagement.hibernateUtil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * This class create sessionFactory.
 * This class enables to create a worker that works with the DB.
 * @author Shoham Roditi
 * @author Sveta Gimpelson
 */

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try
        {
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable err){
            System.err.println("sessionFactory creation was failed: " + err);
            throw new ExceptionInInitializerError(err);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}