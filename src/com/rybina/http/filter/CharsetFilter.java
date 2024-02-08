package com.rybina.http.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter(value = "/*", servletNames = {"registrationServlet"},
        initParams = @WebInitParam(name = "param", value = "paramValues"),
        dispatcherTypes = DispatcherType.REQUEST // dispatcherTypes, как мы и обсуждали раннее, это может быть redirect, forward и include
)
public class CharsetFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.name());
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
