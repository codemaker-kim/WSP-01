package org.project.smuvote.service;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.project.smuvote.DTO.AddUserRequest;
import org.project.smuvote.domain.User;
import org.project.smuvote.util.ConnectionUtil;
import org.project.smuvote.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@NoArgsConstructor
@AllArgsConstructor
public class UserService {
    private ConnectionUtil connectionUtil;
    private SessionUtil sessionUtil;

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

    public User findUserByInfo (AddUserRequest dto) throws Exception {
        String sql = "select * from users where username = ? and password = ?";

        @Cleanup Connection connection = connectionUtil.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, dto.getUsername());
        preparedStatement.setString(2, dto.getPassword());

        @Cleanup ResultSet rs = preparedStatement.executeQuery();

        rs.next();

        return (User) rs.getObject(1);
    }

    public Long getUserIdBySession(HttpServletRequest request) throws Exception {
        String username = (String) sessionUtil.getSession(request).getAttribute("username");

        String sql = "select id * from users where username = ?";
        @Cleanup Connection connection = connectionUtil.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, username);

        @Cleanup ResultSet rs = preparedStatement.executeQuery();

        rs.next();

        return rs.getLong("id");
    }
}
