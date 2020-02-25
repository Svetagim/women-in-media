package com.roditigimpelson.usersmanagement.dao;

import com.roditigimpelson.usersmanagement.exceptions.UserDaoException;
import com.roditigimpelson.usersmanagement.hibernateUtil.HibernateUtil;
import com.roditigimpelson.usersmanagement.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * A user crud DB operations implementation
 * @author Shoham Roditi
 * @author Sveta Gimpelson
 */
public class UserDao implements UserDaoInterface {

    /**
     * open new session
     * @return Session
     */
    private Session OpenNewSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * hashing the password
     * @param password - entered user password String
     * @return String as hashed password
     */
    private String hash(String password) {
        if (password == null){
            return "";
        }
        String key = "Th!$iSmYK3y";
        byte[] byteKey = key.getBytes();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException err){
            System.out.println("Error hashing the password");
            return "";
        }
        md.update(byteKey);
        byte[] hashedPass = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return new String(hashedPass);
    }

    /**
     * getter of hashing password
     * @param password
     * @return String
     */
    public String getHashCode(String password){
        return hash(password);
    }

    /**
     * Save the user in the DB
     * @param user
     * @return boolean - if creation was success return true, else return false
     * @throws UserDaoException
     */
    @Override
    public boolean saveUser(User user) throws UserDaoException {
        User checkExistUser = findByUsername(user.getUsername());
        if (checkExistUser == null){
            Transaction transaction = null;
            try (Session session = OpenNewSession()) {
                transaction = session.beginTransaction();
                session.save(user);
                transaction.commit();
                return true;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw new UserDaoException("UserDao failed to save user");
            }
        }
        return false;
    }

    /**
     * Update the user tuple in the DB
     * @param user
     * @throws UserDaoException
     */
    @Override
    public void updateUser(User user) throws UserDaoException {
        User checkExistUser = findByUsername(user.getUsername());
        if (checkExistUser == null){
            System.out.println("no user found to update");
            return;
        }
        Transaction transaction = null;
        try (Session session = OpenNewSession()){
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new UserDaoException("UserDao failed to update user");
        }
    }

    /**
     * Delete the user tuple from DB
     * @param username
     * @throws UserDaoException
     */
    @Override
    public void deleteUser(String username) throws UserDaoException {
        Transaction transaction = null;
        try (Session session = OpenNewSession()){
            transaction = session.beginTransaction();
            User user = session.get(User.class, username);
            if (user != null){
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception err){
            if (transaction != null) {
                transaction.rollback();
            }
            throw new UserDaoException("UserDao failed to delete user");
        }
    }

    /**
     * Gets the user tuple from the DB by username
     * @param username
     * @return User - tuple from the DB
     * @throws UserDaoException
     */
    @Override
    public User findByUsername(String username) throws UserDaoException {
        Transaction transaction = null;
        try (Session session = OpenNewSession()){
            transaction = session.beginTransaction();
            User user = session.get(User.class, username);
            transaction.commit();
            return user;
        } catch (Exception err){
            if (transaction != null){
                transaction.rollback();
            }
            throw new UserDaoException("UserDao failed to search for username: " + username);
        }
    }

    /**
     * Get all the users from the DB
     * @return list of all users in DB
     * @throws UserDaoException
     */
    @Override
    public List<User> findAll() throws UserDaoException {
        Transaction transaction = null;
        try(Session session = OpenNewSession()){
            transaction = session.beginTransaction();
            List<User> allUsers;
            allUsers = session.createQuery("from User").list();
            transaction.commit();
            return allUsers;
        } catch (Exception err){
            if (transaction != null){
                transaction.rollback();
            }
            throw new UserDaoException("UserDao failed to get all users");
        }
    }

    /**
     * Check if the username and password that entered are as same as in the user tuple in the DB.
     * @param username
     * @param password
     * @return boolean - return true if user exists in the DB and his password is correct, else false
     * @throws UserDaoException
     */
    @Override
    public boolean isAuthenticate(String username, String password) throws UserDaoException {
        boolean isValidInput = username != null && password != null;
        if (!isValidInput) {
            throw new UserDaoException("Username or Password were empty");
        }
        Transaction transaction = null;
        try (Session session = OpenNewSession()){
            transaction = session.beginTransaction();
            User user = session.get(User.class, username);
            transaction.commit();
            if (user == null)
                return false;
            String checkPass = hash(password);
            return user.getPassword().equals(checkPass);
        } catch (Exception err){
            if (transaction != null){
                transaction.rollback();
            }
            throw new UserDaoException("UserDao failed to check authentication");
        }
    }
}