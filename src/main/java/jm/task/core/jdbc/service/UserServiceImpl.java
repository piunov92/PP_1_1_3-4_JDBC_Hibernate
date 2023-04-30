package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl extends UserDaoJDBCImpl implements UserService {
    public void createUsersTable() {
        super.createUsersTable();
    }

    public void dropUsersTable() {
        super.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        super.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        super.removeUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> users =  super.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    public void cleanUsersTable() {
        super.cleanUsersTable();
    }
}
