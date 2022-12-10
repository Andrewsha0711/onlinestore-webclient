package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import dao.ProductCardDAO;
import service.ProductCardService;
import service.UserService;

@Configuration
@ComponentScan("controller")
public class ServiceBeansConfig {
	@Bean
	public ProductCardDAO productCardDAO() {
		return new ProductCardDAO();
	}
	@Bean
	public ProductCardService productCardService() {
		return new ProductCardService();
	}
	@Bean
	public UserService userService() {
		return new UserService();
	}
}
