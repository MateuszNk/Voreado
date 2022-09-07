package io.github.MateuszNk.voreado.domain.discovery;

import io.github.MateuszNk.voreado.config.DataSourceProvider;
import io.github.MateuszNk.voreado.domain.common.BaseDao;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscoveryDao extends BaseDao {
    public List<Discovery> findAll() {
        final String query = """
    SELECT
        id, title, url, description, date_added, category_id, user_id
    FROM
        discovery d
    """;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Discovery> allDiscoveries = new ArrayList<>();
            while (resultSet.next()) {
                Discovery discovery = mapRow(resultSet);
                allDiscoveries.add(discovery);
            }
            return allDiscoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Discovery mapRow(ResultSet resultSet) throws SQLException {
        int discoveryId = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String url = resultSet.getString("url");
        String description = resultSet.getString("description");
        LocalDateTime dateAdded = resultSet.getTimestamp("date_added").toLocalDateTime();
        int categoryId = resultSet.getInt("category_id");
        int userId = resultSet.getInt("user_id");
        return new Discovery(discoveryId, title, url, description, dateAdded, categoryId, userId);
    }

    public List<Discovery> findByCategory(int categoryId) {
        final String query = """
                SELECT
                id, title, url, description, date_added, category_id, user_id
                FROM
                discovery
                WHERE
                category_id = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Discovery> discoveries = new ArrayList<>();
            while (resultSet.next()) {
                Discovery discovery = mapRow(resultSet);
                discoveries.add(discovery);
            }
            return discoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Discovery discovery) {
        final String query = """
                INSERT INTO
                    discovery (title, url, description, date_added, category_id, user_id)
                VALUES
                    (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, discovery.getTitle());
            preparedStatement.setString(2, discovery.getUrl());
            preparedStatement.setString(3, discovery.getDescription());
            preparedStatement.setObject(4, discovery.getDateAdded());
            preparedStatement.setInt(5, discovery.getCategoryId());
            preparedStatement.setInt(6, discovery.getUserId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                discovery.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }
}
