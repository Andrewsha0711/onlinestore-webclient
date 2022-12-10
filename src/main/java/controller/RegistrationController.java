package controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import config.WebConfig;
import model.User;

@Controller
public class RegistrationController {
    @PostMapping("/registration")
    public String createUser(@RequestParam("username") String username,
            @RequestParam("name") String name, @RequestParam("password") String password,
            @RequestParam("secondPassword") String secondPassword, HttpSession session,
            HttpServletRequest request) {

        User user = new User();
        // TODO catch else expressions
        if (Pattern.matches(WebConfig.emailRegexp, username)) {
            user.setEmail(username);
        }
        if (Pattern.matches(WebConfig.phoneNumberRegexp, username)) {
            user.setEmail(username);
        }
        if (Pattern.matches(WebConfig.passwordRegexp, password)) {
            user.setPassword(password);
        }
        session.setAttribute("user", user);

        // Redirecting to previous page
        String referer = request.getHeader("Referer");
        if (referer == null) {
            referer = "/";
        }
        return "redirect:" + referer;
    }
}
