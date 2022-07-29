package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import models.User;

@Controller
public class AutorizationController {
	@PostMapping("/autorization")
	public String checkUser(@RequestParam("email") String email,
			                @RequestParam("password") String password,
			                Model model,
			                HttpSession session,
			                HttpServletRequest request){
		//Find by email
		//Check password
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		session.setAttribute("user", user);
		//Redirecting to previous page
		String referer = request.getHeader("Referer");
		if(referer == null) {
			referer = "/";
		}
	    return "redirect:"+ referer;
	}
}