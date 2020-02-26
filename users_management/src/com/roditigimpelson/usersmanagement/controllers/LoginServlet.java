package com.roditigimpelson.usersmanagement.controllers;

import com.roditigimpelson.usersmanagement.dao.UserDao;
import com.roditigimpelson.usersmanagement.exceptions.UserDaoException;
import com.roditigimpelson.usersmanagement.models.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * A servlet that checks if the user exists in the DB and if the password and username entered correctly.
 * isAuthenticate checks if the username and the password that entered equals to the tuple in the DB.
 * @see javax.servlet.http.HttpServlet
 */
@WebServlet(name= "/LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    static protected final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        boolean isValid = false;
        try {

            isValid = userDao.isAuthenticate(userName, password);
        } catch (UserDaoException e) {
            e.printStackTrace();
        }
        if (isValid) {
            User user = null;
            try {
                user = userDao.findByUsername(userName);
            } catch (UserDaoException e) {
                e.printStackTrace();
            }
            session.setAttribute("user", user);
            Cookie cookie = new Cookie("username", userName);
            int cookieActiveTime = 60*60;
            cookie.setMaxAge(cookieActiveTime);
            resp.addCookie(cookie);
            resp.sendRedirect("UserLogged.jsp");
            return;
        }
        resp.sendRedirect("InvalidLogin.jsp");
    }
}