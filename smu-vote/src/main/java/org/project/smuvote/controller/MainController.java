package org.project.smuvote.controller;

import lombok.NoArgsConstructor;
import org.project.smuvote.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "mainController", value = "/main")
@NoArgsConstructor
public class MainController extends HttpServlet {

    SessionUtil sessionUtil = new SessionUtil();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = sessionUtil.getSession(request);

        if(session.getAttribute("username") == null)
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        else
            request.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(request, response);
    }
}
