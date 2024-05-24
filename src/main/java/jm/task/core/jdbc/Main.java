package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("John", "Jameson", (byte) 83);
        userService.saveUser("Paul", "McCartney", (byte) 81);
        userService.saveUser("Maisie", "Williams", (byte) 27);
        userService.saveUser("Bill", "Gates", (byte) 68);
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
