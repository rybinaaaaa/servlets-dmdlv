package com.rybina.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/cookies")
public class CookieServlet extends HttpServlet {

    private final String UNIQUE_ID = "userId";
    private static final AtomicInteger counter = new AtomicInteger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        if (cookies == null || Arrays.stream(cookies).filter(cookie -> UNIQUE_ID.equals(cookie.getName())).findFirst().isEmpty()) {
            Cookie cookie = new Cookie(UNIQUE_ID, "1");
            cookie.setPath("/cookies"); // если мы не установим путь, то куки будет видно по домену сервера (localhost)
//            cookie.setMaxAge(-1); кука будет доступна пока браузер не закроется
            cookie.setMaxAge(15 * 60); // кука будет жить 15 минут
            cookie.setHttpOnly(true); // кукинедоступен java script

            resp.addCookie(cookie);

            counter.incrementAndGet();
        }

        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(counter.get());
        }
    }
}
