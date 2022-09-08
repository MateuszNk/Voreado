package io.github.MateuszNk.voreado.domain.vote;

import io.github.MateuszNk.voreado.domain.common.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoteDao extends BaseDao {

    public void save(Vote vote) {
        final String query = """
                INSERT INTO
                    vote (user_id, discovery_id, type, date_added)
                VALUES
                    (?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    type = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, vote.getUserId());
            preparedStatement.setInt(2, vote.getDiscoveryId());
            preparedStatement.setString(3, vote.getType().toString());
            preparedStatement.setObject(4, vote.getDateAdded());
            preparedStatement.setString(5, vote.getType().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countByDiscoveryId(int discoveryId) {
        final String query = """
                SELECT
                    (SELECT COUNT(discovery_id) FROM vote WHERE discovery_id = ? AND type = 'UP')
                    -
                    (SELECT COUNT(discovery_id) FROM vote WHERE discovery_id = ? AND type = 'DOWN')
                AS
                    vote_count;
                """;
        try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, discoveryId);
            preparedStatement.setInt(2, discoveryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("vote_count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
