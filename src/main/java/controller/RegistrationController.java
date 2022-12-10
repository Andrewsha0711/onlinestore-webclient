package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import classes.TelegramBotController;
import classes.TelegramBotMembership;
import dao.UserDAO;
import models.User;

@Controller
public class RegistrationController {
	private final UserDAO userDAO;

	@Autowired
	public RegistrationController(UserDAO userDao) {
		this.userDAO = userDao;
	}

//	@GetMapping("/registration")
//	public String registration(Model model) {
//		model.addAttribute("user", new User());
//		return "registration_form";
//	}

	@PostMapping("/registration")
	public String createUser(@RequestParam("email") String email,
            @RequestParam("username") String username, 
            @RequestParam("password") String password,
            @RequestParam("secondPassword") String secondPassword, 
            HttpSession session,
            HttpServletRequest request) {
	
//		int id = this.userDAO.saveUser(newUser);
//		newUser.setId(id);

		User user = new User();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		session.setAttribute("user", user);
		//Redirecting to previous page
		String referer = request.getHeader("Referer");
		if(referer == null) {
			referer = "/";
		}
	    return "redirect:"+ referer;
	}

//	@GetMapping("/telegram_confirmation")
//	public String telegramConfirmation(Model model, HttpSession session) {
//		return "telegram_confirmation_form";
//	}
//
//	@PostMapping("/telegram_confirmation")
//	public String confirm(@RequestParam("telegram") String telegram, Model model, HttpSession session) {
//		User user = (User) session.getAttribute("user");
//		session.removeAttribute("user");
//		TelegramBotMembership member;
//		if ((member = new TelegramBotController().getTelegramBotMembership(telegram)) != null) {
//			user.setTelegram(member);
//			//Save telegram by user id
//			new TelegramBotController().sendMessage(user.getTelegram().getChatId(), "Вы успешно зарегестрировались");
//		}
//		return "redirect:/";
//		// return "succes_page";
//	}
}
