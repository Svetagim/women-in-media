package com.roditigimpelson.usersmanagement.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * A servlet that enables the user to logout and delete the cookie.
 * @see javax.servlet.http.HttpServlet
 */
@WebServlet(name = "/LogoutServlet", urlPatterns = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        Cookie[] cookies = request.getCookies();
        Cookie userCookie = null;
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("username")){
                userCookie = cookie;
                break;
            }
        }
        if (userCookie != null)
            userCookie.setMaxAge(0);
        session.invalidate();
        response.addCookie(userCookie);
        response.sendRedirect("LoginPage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
