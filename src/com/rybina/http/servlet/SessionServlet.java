package com.rybina.http.servlet;

import com.rybina.http.dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/sessions")
public class SessionServlet extends HttpServlet {

    public static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getAttribute()
//        req.getServletContext().getAttribute()
        HttpSession session = req.getSession();
        UserDto user = (UserDto) session.getAttribute(USER);
        if (user == null) {
            user = UserDto.builder().id(25L).mail("test@gmail.com").build();
            session.setAttribute(USER, user);
        }
    }
}
