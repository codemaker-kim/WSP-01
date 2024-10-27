package org.project.smuvote.service;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.project.smuvote.DTO.AddVoteRequest;
import org.project.smuvote.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

@AllArgsConstructor
@NoArgsConstructor
public class VoteService {
    private ConnectionUtil connectionUtil;

    public void save(AddVoteRequest dto) throws Exception {
        String sql = "insert into vote (creator_id, title) values (?, ?)";
        @Cleanup Connection connection = connectionUtil.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, dto.getCreatorId());
        preparedStatement.setString(2, dto.getTitle());

        preparedStatement.executeUpdate();
    }

}
