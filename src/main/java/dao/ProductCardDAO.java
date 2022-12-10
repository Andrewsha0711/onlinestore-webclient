package dao;

import java.net.URI;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import model.ProductCard;

@Component
public class ProductCardDAO {

    @Value("${api.url.productcard}")
    private String productCardEndpoint;

    public ProductCardDAO() {}

    public ArrayList<ProductCard> getPageOfProductCards(int page, int size) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromUriString(this.productCardEndpoint)
                .queryParam("page", page).queryParam("size", size).build().toUri();
        JsonNode jsonNode = restTemplate.getForObject(uri, JsonNode.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (jsonNode.has("empty")) {
                if (!jsonNode.get("empty").asBoolean()) {
                    TypeFactory typeFactory = objectMapper.getTypeFactory();
                    String content = jsonNode.get("content").toString();
                    return objectMapper.readValue(content, typeFactory
                            .constructCollectionType(ArrayList.class, ProductCard.class));
                }
            }
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public ProductCard getProductCard(String id) {
        URI uri = UriComponentsBuilder.fromUriString(this.productCardEndpoint).path("/" + id)
                .build().toUri();
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = restTemplate.getForObject(uri, JsonNode.class);
        try {
            if (jsonNode.has("data")) {
                return objectMapper.readValue(jsonNode.get("data").toString(), ProductCard.class);
            }
            if (jsonNode.has("errors")) {
                // TODO show errors + log
            }
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
