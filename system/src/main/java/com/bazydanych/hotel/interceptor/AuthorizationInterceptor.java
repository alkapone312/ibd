package main.java.com.bazydanych.hotel.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.com.bazydanych.hotel.database.Connector;
import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Client;
import main.java.com.bazydanych.hotel.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        token = token == null ? "" : token;
        String[] splittedToken = token.split(":");
        if(splittedToken.length == 3) {
            Client client = new ClientRepository(new DatabaseQuery(new Connector()))
                .getClient(Integer.parseInt(splittedToken[0]));

            if(client.getPassword().equals(splittedToken[1])) {
                return true;
            }
        }

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write("{\"error\": \"Access forbidden. You are not authorized.\", \"code\": 403}");

        return false;
    }
}
