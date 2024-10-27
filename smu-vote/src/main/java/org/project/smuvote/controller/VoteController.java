package org.project.smuvote.controller;

import org.project.smuvote.service.VoteService;
import org.project.smuvote.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="voteController", value="/vote")
public class VoteController extends HttpServlet {

    SessionUtil sessionUtil = new SessionUtil();
    VoteService voteService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(sessionUtil.getSession(request) != null)
            request.getRequestDispatcher("/WEB-INF/view/vote.jsp").forward(request, response);
        else
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String title = request.getParameter("voteTitle");

    }

}
