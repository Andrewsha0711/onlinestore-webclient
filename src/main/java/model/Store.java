package model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public class Store implements Serializable{
	private static final long serialVersionUID = -231905771414270851L;
	private UUID id;
	private String name;
    private String description;
	private Set<ProductCard> productCards;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<ProductCard> getProductCards() {
		return productCards;
	}
	public void setProductCards(Set<ProductCard> productCards) {
		this.productCards = productCards;
	}
}
