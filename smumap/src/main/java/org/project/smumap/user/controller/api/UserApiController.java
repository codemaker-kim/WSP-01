package org.project.smumap.user.controller.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.project.smumap.user.dto.UserInfoRequest;
import org.project.smumap.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signup(UserInfoRequest request) {

        try{
            userService.UserSave(request);
            return "redirect:/main";
        } catch (IllegalArgumentException e){
            return "signupError";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}
