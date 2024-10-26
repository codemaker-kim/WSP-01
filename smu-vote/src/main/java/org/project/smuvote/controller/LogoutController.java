package org.project.smuvote.controller;

import lombok.AllArgsConstructor;
import org.project.smuvote.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@WebServlet(name = "logoutController", value="/logout")
public class LogoutController extends HttpServlet {

    private final SessionUtil sessionUtil;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sessionUtil.deleteSession(request);
        response.sendRedirect("/main");
    }
}
