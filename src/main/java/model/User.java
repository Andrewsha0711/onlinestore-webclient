package models;

import classes.TelegramBotMembership;

public class User {
	private Integer id;
	private String email;
	private String username;
	private String password;
	private String phoneNumber;
	private String imageURL;
	private TelegramBotMembership telegram;
	
	public User() {
	}
	
	public User(int id, String email, String username) {
		this.id = id;
		this.email = email;
		this.username = username;
	}
	
	//Get methods
	public String getUsername() {
		return this.username;
	}
	public String getEmail() {
		return this.email;
	}
	public String getPassword() {
		return this.password;
	}
	public TelegramBotMembership getTelegram() {
		return this.telegram;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	// Set methods
	public void setId(int value) {
		this.id = value;
	}
	public void setUsername(String value) {
		this.username = value;
	}
	public void setEmail(String value) {
		this.email = value;
	}
	public void setPassword(String value) {
		this.password = value;
	}
	public void setTelegram(TelegramBotMembership value) {
		this.telegram = value;
	}
	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	

}
