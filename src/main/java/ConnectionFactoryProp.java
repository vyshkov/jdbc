import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactoryProp {

    private String url;
    private String user;
    private String pass;
    
    private static ConnectionFactoryProp instance = new ConnectionFactoryProp();

    private ConnectionFactoryProp() {

        ResourceBundle rb = ResourceBundle.getBundle("props.jdbc");
        
        url = rb.getString("jdbc.url");
        user = rb.getString("jdbc.username");
        pass = rb.getString("jdbc.password");
        
        String driverName = rb.getString("jdbc.driverClassName");
        
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error while loading driver");
            ex.printStackTrace();
        }
        
        
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
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
