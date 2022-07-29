package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	private String id;
	private String name;
	private String shortDescription;
	private Double actualPrice;
	private Double discount;
	private Double price;
	
//	private String category;
	private String description;

	private String storeId;
	private String[] imagesURL;
	
	public Double getPrice() {
		if(this.discount != null && this.discount >= 0) {
			return this.actualPrice - this.actualPrice * (this.discount/100);
		}
		else {
			return this.actualPrice;
		}
	}
	public void setPrice(Double price) {
		this.discount = (this.actualPrice - price) / this.actualPrice * 100;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
//	public String getCategory() {
//		return category;
//	}
//	public void setCategory(String category) {
//		this.category = category;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public Double getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(Double price) {
		this.actualPrice = price;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public Object[] getImagesURL() {
		return imagesURL;
	}
	public void setImagesURL(String[] imagesURL) {
		this.imagesURL = imagesURL;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
