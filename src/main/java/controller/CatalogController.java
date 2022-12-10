package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.ProductCardDTO;
import model.User;
import service.ProductCardService;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    private ProductCardService productCardService;

    @Autowired
    public CatalogController(ProductCardService productCardService) {
        this.productCardService = productCardService;
    }

    @GetMapping("/{productId}")
    public String productPage(@PathVariable("productId") String id, Model model,
            HttpSession session) {
        model.addAttribute("user", (User) session.getAttribute("user"));
        // TODO try to get product from cache before send request
        ProductCardDTO productCard = this.productCardService.getProductCard(id);
        model.addAttribute("productCard", productCard);
        return "product_page";
    }
}
