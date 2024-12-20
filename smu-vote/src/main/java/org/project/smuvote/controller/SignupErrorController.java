package org.project.smuvote.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@WebServlet(name = "signupErrorController", value = "/signupError")
public class SignupErrorController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/signupError.jsp").forward(request, response);
    }
}
