package com.rybina.http.servlet;

import com.rybina.http.dto.CreateUserDto;
import com.rybina.http.exception.ValidationException;
import com.rybina.http.service.UserService;
import com.rybina.http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", List.of("USER", "ADMIN"));
        req.setAttribute("gender", List.of("MALE", "FEMALE"));

        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String birthday = req.getParameter("birthday");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String gender = req.getParameter("gender");

        CreateUserDto userDto = CreateUserDto.builder()
                .name(name)
                .email(email)
                .birthday(birthday)
                .password(password)
                .role(role)
                .gender(gender).build();

        try {
            userService.create(userDto);
            resp.sendRedirect("/login");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }
}
