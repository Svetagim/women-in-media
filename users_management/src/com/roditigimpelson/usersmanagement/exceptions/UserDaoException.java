package com.roditigimpelson.usersmanagement.exceptions;

/**
 * This class describes an exception thrown when an error occurred while using userDao.
 * @author Shoham Roditi
 * @author Sveta Gimpelson
 */
public class UserDaoException extends Exception {

    /**
     * Class constructor
     * @param message of type String
     */
    public UserDaoException(String message) {
        super(message);
    }
}