package com.rybina.http.servlet;

import com.rybina.http.dto.ReadUserDto;
import com.rybina.http.service.UserService;
import com.rybina.http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        userService.login(email, password).ifPresentOrElse(
                user -> userOnLoginSuccess(req, resp, user),
                () -> userOnLoginFail(req, resp)
        );
    }

    @SneakyThrows
    private void userOnLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error&email=" + req.getParameter("email"));
    }

    @SneakyThrows
    private void userOnLoginSuccess(HttpServletRequest req, HttpServletResponse resp, ReadUserDto user){
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/hello");
    }
}
