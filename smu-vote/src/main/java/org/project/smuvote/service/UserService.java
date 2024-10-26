package org.project.smuvote.service;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.project.smuvote.DTO.AddUserRequest;
import org.project.smuvote.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@AllArgsConstructor
public class UserService {
    private final ConnectionUtil connectionUtil;

    public boolean isExistUser(String username) throws Exception {
        String sql = "select * from users where username = ?";
        @Cleanup Connection connection = connectionUtil.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, username);

        @Cleanup ResultSet rs = preparedStatement.executeQuery();

        rs.next();

        return rs.next();
    }

    public void save(AddUserRequest dto) throws Exception {
        String sql = "insert into users (username, password), values (?, ?)";
        @Cleanup Connection connection = connectionUtil.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, dto.getUsername());
        preparedStatement.setString(2, dto.getPassword());

        preparedStatement.executeUpdate();
    }
}
