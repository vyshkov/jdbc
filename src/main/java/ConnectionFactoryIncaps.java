
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactoryIncaps {
	//static reference to itself
	private static ConnectionFactoryIncaps instance = new ConnectionFactoryIncaps();
	public static final String URL = "jdbc:mysql://localhost:3306/test";
	public static final String USER = "root";
	public static final String PASSWORD = "1234";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
	
	//private constructor
	private ConnectionFactoryIncaps() {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("ERROR: Unable to Connect to Database.");
                        e.printStackTrace();
                }
		return connection;
	}	
	
	public static Connection getConnection() {
		return instance.createConnection();
	}
}