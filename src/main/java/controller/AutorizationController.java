package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.User;
import service.UserService;

@Controller
public class AutorizationController {
    @Autowired
    private UserService userService;

    @PostMapping("/autorization")
    public String checkUser(@RequestParam("email") String username,
            @RequestParam("password") String password, Model model, HttpSession session,
            HttpServletRequest request, HttpServletResponse response) {
        // Find by email
        // Check password
        User user = this.userService.login(username, password, response);
        session.setAttribute("user", user);
        // Redirecting to previous page
        String referer = request.getHeader("Referer");
        if (referer == null) {
            referer = "/";
        }
        return "redirect:" + referer;
    }
}
