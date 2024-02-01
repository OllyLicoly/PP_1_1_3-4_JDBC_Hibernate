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
        Connection connection = null;

        String sql = "CREATE TABLE IF NOT EXISTS USERS (ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(45), LASTNAME VARCHAR(45), AGE INT(3))";

        try {
            connection = getConnection();
            Statement statement = connection.prepareStatement(sql);

            statement.executeUpdate(sql);
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void dropUsersTable() {
        Connection connection = null;

        String sql = "DROP TABLE USERS";

        try {
            connection = getConnection();
            Statement statement = connection.prepareStatement(sql);

            statement.executeUpdate(sql);
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;

        String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();
            connection.commit();

            System.out.printf("User с именем %s добавлен в базу данных \n", name);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void removeUserById(long id) {

        Connection connection = null;

        String sql = "DELETE FROM USERS WHERE ID = ?";

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            connection.commit();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public List<User> getAllUsers() throws SQLException {
        Connection connection = null;
        List<User> userList = new ArrayList<>();

        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM USERS";
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);

                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return userList;
    }

    public void cleanUsersTable() {

        Connection connection = null;
        String sql = "TRUNCATE TABLE TEST.USERS";

        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
