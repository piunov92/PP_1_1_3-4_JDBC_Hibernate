import jm.task.core.jdbc.util.Util;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {
    Connection connection = null;
    @Test
    public void connectionTestDB() {
        Util util = new Util();
        try {
            connection = util.getConnection();
            System.out.println("Connection OK.");
            connection.close();
            if (connection.isClosed()) {
                System.out.println("Connection closed");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection ERROR.");
        }
    }
}
