import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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

    @Test
    public void getConnectionHibernate() {
        try (Session session = Util.getConnectionHibernate().openSession()) {
            session.beginTransaction();
            System.out.println("Connection OK.");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Connection ERROR.");
        }
    }
}
