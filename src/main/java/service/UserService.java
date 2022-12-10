package service;

import java.net.HttpCookie;
import java.net.URI;
import java.util.Collections;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;

@Service
public class UserService {
	@Value("${api.url.login}")
	private String loginEndpoint;
	@Value("${api.url.user}")
	private String userEndpoint;
	@Autowired
	private RestTemplate restTemplate;

	public User login(String username, String password, HttpServletResponse response) {
		URI uri = UriComponentsBuilder.fromUriString(this.loginEndpoint).build().toUri();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("username", username);
		map.add("password", password);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<User> user = restTemplate.postForEntity(uri, request, User.class);
		user.getHeaders().get(HttpHeaders.SET_COOKIE).forEach(header -> {
			HttpCookie.parse(header).forEach(cookie -> {
				response.addCookie(new Cookie(cookie.getName(), cookie.getValue()));
			});
		});
		return user.getBody();
	}

	public User getMe(String token) {
		URI uri = UriComponentsBuilder.fromUriString(this.userEndpoint).path("/me").build().toUri();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set(HttpHeaders.AUTHORIZATION, token);
		HttpEntity httpEntity = new HttpEntity(headers);
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity,
				JsonNode.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			try {
				if (responseEntity.getBody().has("data")) {
					return objectMapper.readValue(responseEntity.getBody().get("data").toString(), User.class);
				}
				if (responseEntity.getBody().has("errors")) {
					// TODO show errors + log
				}
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
