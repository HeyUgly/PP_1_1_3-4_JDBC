package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        try {
            userService.createUsersTable();
            userService.saveUser("John", "Jameson", (byte) 83);
            userService.saveUser("Paul", "McCartney", (byte) 81);
            userService.saveUser("Maisie", "Williams", (byte) 27);
            userService.saveUser("Bill", "Gates", (byte) 68);
            System.out.println(userService.getAllUsers());
            userService.cleanUsersTable();
            userService.dropUsersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
