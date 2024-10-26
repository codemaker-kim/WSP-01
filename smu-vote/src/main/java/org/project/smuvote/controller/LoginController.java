package org.project.smuvote.controller;

import lombok.AllArgsConstructor;
import org.project.smuvote.util.SessionUtil;

import javax.servlet.annotation.WebServlet;

@AllArgsConstructor
@WebServlet(name = "loginController", value = "/login")
public class LoginController {
    private final SessionUtil sessionUtil;
}
