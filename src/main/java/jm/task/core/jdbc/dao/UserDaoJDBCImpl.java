package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable(){
        String sqlQuery = "CREATE TABLE IF NOT EXISTS user(id BIGINT NOT NULL AUTO_INCREMENT, name varchar(30), lastName varchar(30), age tinyint, PRIMARY KEY (id))";

        try(Statement statement = connection.createStatement()) {
            statement.execute(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sqlQuery = "DROP TABLE IF EXISTS user";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sqlQuery = "INSERT INTO user VALUES(id, ?, ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sqlQuery = "DELETE FROM user WHERE id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM user";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String sqlQuery = "DELETE FROM user";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
