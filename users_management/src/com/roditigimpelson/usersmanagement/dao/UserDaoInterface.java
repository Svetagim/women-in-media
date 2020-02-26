package com.roditigimpelson.usersmanagement.dao;

import com.roditigimpelson.usersmanagement.exceptions.UserDaoException;
import com.roditigimpelson.usersmanagement.models.User;
import java.util.List;

/**
 * User crud DB operations interface
 *  @author Shoham Roditi
 *  @author Sveta Gimpelson
 */
public interface UserDaoInterface {
    /**
     * Save the user in the DB
     * @param entity
     * @return boolean - if creation was success return true, else return false
     * @throws UserDaoException
     */
    boolean saveUser(User entity) throws UserDaoException;

    /**
     * Update the user tuple in the DB
     * @param entity
     * @throws UserDaoException
     */
    void updateUser(User entity) throws UserDaoException;

    /**
     * Delete the user tuple from DB
     * @param username
     * @throws UserDaoException
     */
    void deleteUser(String username) throws UserDaoException;

    /**
     * Gets the user tuple from the DB by username
     * @param username
     * @return User - tuple from the DB
     * @throws UserDaoException
     */
    User findByUsername(String username) throws UserDaoException;

    /**
     * Get all the users from the DB
     * @return list of all users in DB
     * @throws UserDaoException
     */
    List<User> findAll() throws UserDaoException;

    /**
     * Check if the username and password that entered are as same as in the user tuple in the DB.
     * @param username
     * @param password
     * @return boolean - return true if user exists in the DB and his password is correct, else false
     * @throws UserDaoException
     */
    boolean isAuthenticate(String username, String password) throws UserDaoException;
}