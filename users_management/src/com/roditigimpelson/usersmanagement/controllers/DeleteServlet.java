package com.roditigimpelson.usersmanagement.controllers;

import com.roditigimpelson.usersmanagement.dao.UserDao;
import com.roditigimpelson.usersmanagement.exceptions.UserDaoException;
import com.roditigimpelson.usersmanagement.models.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * A servlet that deletes a user by username from DB.
 * @see javax.servlet.http.HttpServlet
 */
@WebServlet(name = "/DeleteServlet", urlPatterns = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    static protected final UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            userDao.deleteUser(user.getUsername());
            session.removeAttribute("user");
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("username")){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    response.sendRedirect("LoginPage.jsp");
                    break;
                }
            }
        } catch (UserDaoException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }
}
