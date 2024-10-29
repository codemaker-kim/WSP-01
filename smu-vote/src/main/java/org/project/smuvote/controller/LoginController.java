package org.project.smuvote.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.project.smuvote.DTO.AddUserRequest;
import org.project.smuvote.service.UserService;
import org.project.smuvote.util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@NoArgsConstructor
@AllArgsConstructor
@WebServlet(name = "loginController", value = "/login")
public class LoginController extends HttpServlet {

    private SessionUtil sessionUtil;
    private UserService userService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AddUserRequest dto = AddUserRequest.builder()
                                .username(username)
                                .password(password)
                                .build();

        try{
            userService.findUserByInfo(dto);
            sessionUtil.setSession(request);
            response.sendRedirect("/main");
        } catch(NullPointerException e){
            response.sendRedirect("/loginError");
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
