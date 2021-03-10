package dataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 
 * clasa care realizeaza conexiunea la baza de date
 *
 */
public class ConnectionClass {
	
	private static final Logger LOGGER = Logger.getLogger(ConnectionClass.class.getName());
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/assignment3?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "root";

	private static ConnectionClass singleInstance = new ConnectionClass();

	private ConnectionClass() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * creaza conexiunea 
	 * @return  returneaza conexiunea 
	 */
	private Connection createConnection() {//creaza conexiunea
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * obtine conexiunea 
	 * @return conexiunea
	 */
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}

	/**
	 * inchide conexiunea
	 * @param connection reprezinta conexiunea care trebuie inchisa
	 */
	public static void close(Connection connection) {//inchide conexiunea
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
	}

	/**
	 * inchide un statement
	 * 
	 * @param statement reprezinta statement-ul care se inchide
	 */
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
	}

	/**
	 * inchide un Resultset
	 * @param resultSet reprezinta ce trebuie inchis
	 */
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
			}
		}
	}

}

