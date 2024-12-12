package org.project.smumap.controller.api;

import org.project.smumap.dto.UserInfoRequest;
import org.project.smumap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApiController {

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

}
