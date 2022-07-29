package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import models.User;

@Component
public class UserDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/onlinestore";
	private static final String USERNAME = "andrewsha";
	private static final String PASSWORD = "07112000";

	private static Connection connection;

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM users");
			
			while(result.next()) {
				users.add(new User(result.getInt("id"),
						result.getString("email"),
						result.getString("username")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	public Integer getIdByEmail(String email) {
		Integer id = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT id FROM users WHERE email = ?;"
					);
			preparedStatement.setString(1, email);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				id = result.getInt("id");
				return id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public Integer saveUser(User user) {
		Integer generatedId = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO public.users("
					+ "	email, username, password)"
					+ "	VALUES (?, ?, ?);"
					);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.executeUpdate();
			
			generatedId = this.getIdByEmail(user.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return generatedId;
	}

	public void getUser(int id) {
		
	}
}
