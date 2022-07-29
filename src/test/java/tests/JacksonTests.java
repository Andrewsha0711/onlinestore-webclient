package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Product;

public class JacksonTests {

	@Test
	public void whenSerializeAndDeserializeUsingJackson_thenCorrect() 
	  throws IOException{
	    Product product = new Product();
	    product.setId("test-id-123-456-789");
	    product.setName("Test name");
	    product.setActualPrice(123456.5);
//	    product.setDescription("Test description: product");
	    product.setDiscount(5.0);
	    ObjectMapper mapper = new ObjectMapper();

	    String jsonProduct = mapper.writeValueAsString(product);
	    Product productFromJson = mapper.readValue(jsonProduct, Product.class);
	    
	    assertEquals(product.getId(), productFromJson.getId());
	}
}
