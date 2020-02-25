package com.roditigimpelson.usersmanagement.controllers;

import com.roditigimpelson.usersmanagement.dao.UserDao;
import com.roditigimpelson.usersmanagement.exceptions.UserDaoException;
import com.roditigimpelson.usersmanagement.models.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A servlet that enables the user to update the firstname and lastname in the user tuple.
 * @see javax.servlet.http.HttpServlet
 */

@WebServlet(name = "/UpdateServlet", urlPatterns = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {

    static protected final UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String FName = request.getParameter("firstname");
        String LName = request.getParameter("lastname");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setFirstname(FName);
        user.setLastname(LName);
        try {
            userDao.updateUser(user);
            session.setAttribute("user", user);
            response.sendRedirect("UserLogged.jsp");
        } catch (UserDaoException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doPost(request, response);
    }
}
