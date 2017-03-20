package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kasper
 */
public class DBConnector {

    private Connection connection = null;

    private static final String IP = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public DBConnector() throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE;
        this.connection = (Connection) DriverManager.getConnection(url, USERNAME, PASSWORD);
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void dropConnection() throws SQLException {
        connection.close();
    }

}
