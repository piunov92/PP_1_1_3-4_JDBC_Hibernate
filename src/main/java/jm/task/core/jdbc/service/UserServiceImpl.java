package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoJDBCImpl();
    UserDao hibernateDao = new UserDaoHibernateImpl();
    public void createUsersTable() {
        //dao.createUsersTable();
        hibernateDao.createUsersTable();
    }

    public void dropUsersTable() {
        //dao.dropUsersTable();
        hibernateDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        //dao.saveUser(name, lastName, age);
        hibernateDao.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        //dao.removeUserById(id);
        hibernateDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        //List<User> users =  dao.getAllUsers();
        List<User> users =  hibernateDao.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    public void cleanUsersTable() {
        //dao.cleanUsersTable();
        hibernateDao.cleanUsersTable();
    }
}
