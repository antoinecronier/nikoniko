package com.tactfactory.nikoniko.manager.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;

import com.tactfactory.nikoniko.config.Configuration;

public class MySQLAccess {
	private Connection connect = null;

	private static final String DB_GENERATOR_PATH = "database/nikoniko.sql";

	/** Constructeur prive */
	private MySQLAccess() {
		try {
			if (Configuration.getInstance().isTesting()) {
				
				createDatabase();
			}
			
			connectDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** Instance unique pre-initialisee */
	private static MySQLAccess INSTANCE = new MySQLAccess();

	/** Point d'acces pour l'instance unique du singleton */
	public static MySQLAccess getInstance() {
		return INSTANCE;
	}

	private void connectDataBase() throws Exception {
		if (this.connect == null) {
			this.connect = this.createDBConnexion(true);
		}
		
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
	

	public void createDatabase() throws Exception{
		String sqlTables = "";
		String createQuery = "";
		String thisLine = null;
		String workingDir = System.getProperty("user.dir");
		String path = workingDir + MySQLAccess.DB_GENERATOR_PATH;
		int result = 0;
		
		//Store query from text file to string "query"
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			// open input stream test.txt for reading purpose.
			while ((thisLine = br.readLine()) != null && !thisLine.trim().startsWith("USE ")) {
				//condition pour supprimer les commentaires de la query (!!! uniqument des # ici)
				if (!thisLine.trim().startsWith("#") && !thisLine.trim().startsWith("USE ")) {
					
					createQuery += thisLine;
				}
			}
			while ((thisLine = br.readLine()) != null) {
				//condition pour supprimer les commentaires de la query (!!! uniqument # ici)
				if (!thisLine.trim().startsWith("#")) {
					sqlTables += thisLine;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		String dbName = Configuration.getInstance().getDBName();
		createQuery = createQuery.replaceAll("nikoniko_db_name;",dbName+";");
				
		//A test is running
		if (Configuration.getInstance().isTesting()) {
			try (Connection connection = this.createDBConnexion(false)) {
				if (this.connect != null) {
					this.connect.close();
					this.connect = null;
				}

				Statement statement = connection.createStatement();
				statement.executeUpdate(createQuery); // drop & create DTB
				statement.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			} finally {
				this.connectDataBase();
				
				Statement statement = this.connect.createStatement();
				statement.execute(sqlTables); //create tables
				statement.close();
			}
		}
	}
	
	
	private Connection createDBConnexion(Boolean onDatabase) {
		Connection connection = null;
		
		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Setup the connection with the DB
		Map<String, String> map = Configuration.getInstance().getMap();
		String user = map.get("user");
		String password = map.get("password");
		String driver = map.get("db_driver");
		String host = map.get("db_host");

		String url = null;
		
		if (onDatabase) {
			
			String database = map.get("db_name");
			url = String.format("jdbc:%s://%s/%s?user=%s&password=%s",
			        driver, host, database, user, password);
		} else {
			
			url = String.format("jdbc:%s://%s?user=%s&password=%s",
			        driver, host, user, password);
		}
		
		Properties props = new Properties();
		props.setProperty("allowMultiQueries", "true");
		
		try {
			connection = DriverManager.getConnection(url, props);
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return connection;
	}
}
