package io.github.MateuszNk.voreado.domain.user;

import io.github.MateuszNk.voreado.domain.common.BaseDao;

import java.sql.*;

public class UserDao extends BaseDao {

    public void save(User user) {
        saveUser(user);
        saveUserRole(user);
    }

    private void saveUser(User user) {
        final String query = """
                INSERT INTO
                user (username, email, password, registration_date)
                VALUES
                (?, ?, ?, ?)
                """;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setObject(4, user.getRegistrationDate());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveUserRole(User user) {
        final String query = """
                INSERT INTO
                user_role (username)
                VALUES
                (?)
                """;
        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
