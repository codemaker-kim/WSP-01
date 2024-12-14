package org.project.smumap.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/login-error")
    public String loginError() {
        return "loginError";
    }

    @GetMapping("/signup-error")
    public String signupError() {
        return "signupError";
    }

    @GetMapping("/main")
    public String MoveMain() {
        return "main";
    }
}
