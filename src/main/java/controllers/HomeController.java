package controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dao.ProductDAO;
import models.Product;
import models.User;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String homePage(Model model, HttpSession session){
		model.addAttribute("user", (User)session.getAttribute("user"));
		return "home_page";
	}
	
	@GetMapping("/catalog")
	public String productsByCategoryPage(Model model, HttpSession session){
		model.addAttribute("user", (User)session.getAttribute("user"));
		ArrayList<Product> products = new ProductDAO().getProducts(1, 20);
//		String baseURL = ServletUriComponentsBuilder
//				.fromCurrentContextPath().build().toUriString();
//		model.addAttribute("baseURL", baseURL);
		model.addAttribute("products", products);
		return "category_catalog";
	}
}
