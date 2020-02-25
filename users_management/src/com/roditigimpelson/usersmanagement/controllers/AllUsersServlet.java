package com.roditigimpelson.usersmanagement.controllers;

import com.roditigimpelson.usersmanagement.dao.UserDao;
import com.roditigimpelson.usersmanagement.exceptions.UserDaoException;
import com.roditigimpelson.usersmanagement.models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * A servlet that gets all users data from DB.
 * @see javax.servlet.http.HttpServlet
 */
@WebServlet(name = "/AllUsersServlet", urlPatterns = "/AllUsersServlet")
public class AllUsersServlet extends HttpServlet {

    static protected final UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<User> users = null;
        try {
            users = userDao.findAll();
        } catch (UserDaoException e) {
            e.printStackTrace();
        }
        session.setAttribute("allUsers", users);
    }
}
