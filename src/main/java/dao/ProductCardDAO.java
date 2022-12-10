package dao;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import models.Product;

public class ProductDAO {
	
	// Получение товаров нужной страницы
	public ArrayList<Product> getProducts(int page, int limit) {
		// limit - количество товаров на одной странице
		// Нумерация страниц товаров 1,2,3...
		int offset = (page-1)*limit;
		ArrayList<Product> products = new ArrayList<Product>();
		RestTemplate restTemplate = new RestTemplate();
		String url  = "http://localhost:8080/api/v1/product/";
		String jsonProducts = restTemplate.getForObject(url, String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		TypeFactory typeFactory = objectMapper.getTypeFactory();
		try {
			products = objectMapper.readValue(jsonProducts, typeFactory.constructCollectionType(ArrayList.class, Product.class));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	
	public Product getProduct(String id) {
		String url = "http://localhost:8080/api/v1/product/" + id;
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonProduct = restTemplate
				.getForObject(url, String.class);
		try {
			return objectMapper.readValue(jsonProduct, Product.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
