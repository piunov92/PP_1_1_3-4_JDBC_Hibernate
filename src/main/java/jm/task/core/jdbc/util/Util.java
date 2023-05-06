package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static SessionFactory session;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pp_1_1_3-4_jdbc";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
    public static SessionFactory getConnectionHibernate() {
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.url", DB_URL);
            properties.setProperty("hibernate.connection.username", DB_USERNAME);
            properties.setProperty("hibernate.connection.password", DB_PASSWORD);
            properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            properties.setProperty("show_sql", "true");

            return new Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
    }
}
