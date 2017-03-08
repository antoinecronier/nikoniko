package com.tactfactory.nikoniko.manager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import com.tactfactory.nikoniko.config.Configuration;

public class MySQLAccess {
	private Connection connect = null;
	public static final String DATABASE = "nikoniko";

	private Configuration config;
	
	/** Constructeur priv� */
	private MySQLAccess() {
		this.config = new Configuration();
		try {
			connectDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** Instance unique pr�-initialis�e */
	private static MySQLAccess INSTANCE = new MySQLAccess();

	/** Point d'acc�s pour l'instance unique du singleton */
	public static MySQLAccess getInstance() {
		return INSTANCE;
	}

	private void connectDataBase() throws Exception {
		// This will load the MySQL driver, each DB has its own driver
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		Map<String, String> map = config.getMap();
		String user = map.get("user");
		if(user==null) {
				user = "root";
		}
		String password = map.get("password");
		if(password==null) {
			password = "test";
		}
		connect = DriverManager
				.getConnection("jdbc:mysql://localhost/" + DATABASE + "?" + "user="+user+"&password="+password);
	}

	public ResultSet resultQuery(String query) {
		ResultSet resultSet = null;
		Statement statement = null;
		try {

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery(query);
			return resultSet;
		}

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultSet;
	}

	public void updateQuery(String query) {
		int result = 0;
		Statement statement = null;
		try {

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			result = statement.executeUpdate(query);
		}

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}