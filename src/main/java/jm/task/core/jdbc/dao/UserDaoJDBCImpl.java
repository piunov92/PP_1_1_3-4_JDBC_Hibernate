package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS USERS (" +
                    "ID BIGINT(20) unsigned NOT NULL AUTO_INCREMENT, " +
                    "NAME VARCHAR(45) NOT NULL, " +
                    "LAST_NAME VARCHAR(45) NOT NULL, " +
                    "AGE TINYINT(3) NOT NULL, " +
                    "PRIMARY KEY (ID))");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection ERROR.");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = getConnection().createStatement()) {
                statement.executeUpdate("DROP TABLE IF EXISTS USERS");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection ERROR.");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERS (NAME, LAST_NAME, AGE) VALUES(?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("В таблице с именем \"USERS\" запись с таким \"ID\" уже существует.");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection ERROR.");
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM USERS  WHERE ID = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() != 1) {
                System.out.println("Записи в таблице с таким \"ID\" нет.");
            }
        } catch (SQLSyntaxErrorException e) {
            System.out.println("В базе данных нет таблицы с именем \"USERS\".");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection ERROR.");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Statement statement = getConnection().createStatement()) {
            ResultSet r = statement.executeQuery("SELECT ID, NAME, LAST_NAME, AGE FROM USERS");
            while (r.next()) {
                User user = new User();
                user.setId(r.getLong("ID"));
                user.setName(r.getString("NAME"));
                user.setLastName(r.getString("LAST_NAME"));
                user.setAge(r.getByte("AGE"));
                userList.add(user);
            }
        } catch (SQLSyntaxErrorException e) {
                System.out.println("В базе данных нет таблицы с именем \"USERS\".");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection ERROR.");
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement preparedStatement = getConnection().createStatement()) {
            preparedStatement.execute("TRUNCATE TABLE USERS");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection ERROR.");
        }
    }
}
