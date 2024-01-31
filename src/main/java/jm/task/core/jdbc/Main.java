package jm.task.core.jdbc;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
//import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws SQLException {
//        Util.getConnection();

        UserService userService = new UserServiceImpl();

//        userService.createUsersTable();

//        userService.saveUser("Leonard", "Hofstadter", Byte.parseByte("25"));
//        userService.saveUser("Sheldon", "Cooper", Byte.parseByte("23"));
//        userService.saveUser("Howard", "Wolowitz", Byte.parseByte("24"));
//        userService.saveUser("Rajesh", "Koothrappali", Byte.parseByte("26"));

        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }

//        userService.removeUserById(4);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();

//        List<User> userList1 = userService.getAllUsers();
//        for (User user : userList1) {
//            System.out.println(user);
//        }




    }


}
