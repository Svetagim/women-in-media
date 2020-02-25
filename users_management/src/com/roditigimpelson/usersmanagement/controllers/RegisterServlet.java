package com.roditigimpelson.usersmanagement.controllers;

import com.roditigimpelson.usersmanagement.dao.UserDao;
import com.roditigimpelson.usersmanagement.exceptions.UserDaoException;
import com.roditigimpelson.usersmanagement.models.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * A servlet that enables the user to register and create a cookie.
 * @see javax.servlet.http.HttpServlet
 */
@WebServlet(name = "/RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    static protected final UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        HttpSession session = request.getSession();
        if (userName.length() == 0 || password.length() == 0){
            response.sendRedirect("InvalidRegister.jsp");
            return;
        }
        User user = new User(userName,firstName,lastName,password);
        user.setPassword(userDao.getHashCode(user.getPassword()));
        try {
            if (userDao.saveUser(user)){
                System.out.println("user created");
                Cookie cookie = new Cookie("username", user.getUsername());
                response.addCookie(cookie);
                session.setAttribute("user", user);
                response.sendRedirect("UserLogged.jsp");
                return;
            }
            System.out.println("user already exists");
            response.sendRedirect("InvalidRegister.jsp");
        } catch (UserDaoException | IOException e) {
            e.printStackTrace();
        }
    }
}
