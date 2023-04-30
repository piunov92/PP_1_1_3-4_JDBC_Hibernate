package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserService user = new UserServiceImpl();
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        user.createUsersTable();

        user.saveUser("Пётр", "Гущин", (byte) 25);
        user.saveUser("Геннадий", "Комиссаров", (byte) 18);
        user.saveUser("Феликс", "Романов", (byte) 34);
        user.saveUser("Ярослав", "Шкраба", (byte) 48);

        System.out.println();

        user.getAllUsers();

        user.removeUserById(3);

        user.cleanUsersTable();

        user.dropUsersTable();

    }
}