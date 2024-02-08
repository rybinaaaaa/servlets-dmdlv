package com.rybina.http.servlet;

import com.rybina.http.dto.CreateUserDto;
import com.rybina.http.exception.ValidationException;
import com.rybina.http.service.UserService;
import com.rybina.http.util.JspHelper;
import com.rybina.http.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024) // будет сохранять файлы на жесткий диск только в том случае, если файт весит больше одного гб
@WebServlet(value = UrlPath.REGISTRATION, name = "registrationServlet")
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

        Part image = req.getPart("image");

//        По умолячанию сервлеты не умеют работать с Part

        CreateUserDto userDto = CreateUserDto.builder()
                .name(name)
                .email(email)
                .birthday(birthday)
                .password(password)
                .role(role)
                .gender(gender)
                .image(image)
                .build();

        try {
            userService.create(userDto);
            resp.sendRedirect("/login");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }
}
