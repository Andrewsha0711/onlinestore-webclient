package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.Store;

public class ProductCardDTO implements Serializable {
    private static final long serialVersionUID = 5855902027843170996L;
    public String id;
    public String name;
    public String shortDescription;
    public String description;
    public Product mainProduct;
    public List<Product> otherProducts = new ArrayList<>();
    public String category;
    public Store store;
}
