package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.CacheManager;

import dao.ProductCardDAO;
import dto.ProductCardDTO;
import model.ProductCard;

public class ProductCardService {
	@Autowired
	private ProductCardDAO productCardDAO;
	
	private ProductCardDTO getProductCardDTO(ProductCard productCard) {
		ProductCardDTO productCardDTO = new ProductCardDTO();
		productCardDTO.id = productCard.getId();
		productCardDTO.name = productCard.getName();
		productCardDTO.shortDescription = productCard.getShortDescription();
		productCardDTO.description = productCard.getDescription();
		if(productCard.getProducts() != null) {
			if(!productCard.getProducts().isEmpty()) {
				productCardDTO.mainProduct = productCard.getProducts().get(0);
				productCard.getProducts().remove(0);
			}
			if(!productCard.getProducts().isEmpty()) {
				productCardDTO.otherProducts.addAll(productCard.getProducts());
			}
		}
		productCardDTO.category = productCard.getCategory();
		productCardDTO.store = productCard.getStore();
		
		return productCardDTO;
	}
	
	private List<ProductCardDTO> getPageOfProductCardsDTO(List<ProductCard> productCards){
		List<ProductCardDTO> productCardDTOList = new ArrayList<>();
		for(ProductCard productCard : productCards) {
			productCardDTOList.add(this.getProductCardDTO(productCard));
		}
		return productCardDTOList;
	}
	
	@Cacheable(value="getProductCardPageCache", key="#page")
	public List<ProductCardDTO> getPageOfProductCards(int page, int size){
		return this.getPageOfProductCardsDTO(this.productCardDAO.getPageOfProductCards(page, size));
	}
	
	@Cacheable(value="getProductCardCache", key="#id")
	public ProductCardDTO getProductCard(String id) {
		return this.getProductCardDTO(this.productCardDAO.getProductCard(id));
	}
}
