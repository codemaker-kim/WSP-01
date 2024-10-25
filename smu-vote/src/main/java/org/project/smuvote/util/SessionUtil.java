package org.project.smuvote.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    public HttpSession getSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.getAttribute("username");
        return session;
    }

    public void setSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) request.getAttribute("username");

        session.setAttribute("username", username);
        session.setMaxInactiveInterval(60*60);
    }

    public void deleteSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("username");
        session.invalidate();
    }
}
