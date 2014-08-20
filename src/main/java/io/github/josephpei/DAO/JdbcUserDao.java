package io.github.josephpei.DAO;


import io.github.josephpei.domain.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class JdbcUserDao implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer getMatchCount(String userName, String password) {
        String sql = "SELECT count(*) FROM user_tbl WHERE user_name=? and password=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userName, password);
    }

    public User findUserByName(final String userName) {
        String sql = "select user_id, user_name from user_tbl where user_name=?";
        return jdbcTemplate.queryForObject(sql, new Object[] {userName},
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet resultSet, int i) throws SQLException {
                        User user = new User();
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(resultSet.getString("user_name"));
                        return user;
                    }
                });

    }

    public void updateLoginInfo(User user) {
        String sql = "update user_tbl set last_visit=?, last_ip=? where user_id=?";
        jdbcTemplate.update(sql, user.getLastVisit(), user.getLastIp(), user.getUserId());
    }

    public void insertUser(User user) {
        String sql = "insert into user_tbl (user_name, password, last_visit, last_ip) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getLastVisit(), user.getLastIp());
    }


}
