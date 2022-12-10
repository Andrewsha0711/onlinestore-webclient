package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import model.User;
import service.ProductCardService;

@Controller
public class HomeController {
    private ProductCardService productCardService;

    @Autowired
    public HomeController(ProductCardService productCardService) {
        this.productCardService = productCardService;
    }

    @GetMapping("/")
    public String homePage(Model model, HttpSession session, HttpServletRequest request,
            @CookieValue(required = false, name = "accessToken") Cookie cookie) {
        Cookie[] c = request.getCookies();
        request.getHeaderNames();
        model.addAttribute("user", (User) session.getAttribute("user"));
        return "home_page";
    }

    @GetMapping("/catalog")
    public String productsByCategoryPage(Model model, HttpSession session) {
        model.addAttribute("user", (User) session.getAttribute("user"));
        model.addAttribute("productCards", this.productCardService.getPageOfProductCards(0, 20));
        return "category_catalog";
    }
}
