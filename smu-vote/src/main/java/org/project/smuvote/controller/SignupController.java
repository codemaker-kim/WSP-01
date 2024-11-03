package org.project.smuvote.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.project.smuvote.DTO.AddUserRequest;
import org.project.smuvote.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
@WebServlet(name = "signupController", value = "/signup")
public class SignupController extends HttpServlet {

    private UserService userService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/signup.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try{
            if(userService.isExistUser(username))
                response.sendRedirect("/signupError");
            else{
                AddUserRequest dto = AddUserRequest.builder()
                                    .username(username)
                                    .password(password)
                                    .build();
                userService.save(dto);
            }
        } catch (NullPointerException e) {
            response.sendRedirect("/signupError");
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
