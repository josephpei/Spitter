package io.github.josephpei.Service;

import io.github.josephpei.DAO.JdbcUserDao;
import io.github.josephpei.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JdbcUserDao jdbcUserDao;

    public boolean hasMatchUser(String userName, String password) {
        Long count = jdbcUserDao.getMatchCount(userName, password);
        return count > 0;
    }

    public User findUserByName(String userName) {
        return jdbcUserDao.findUserByName(userName);
    }

    public void loginSuccess(User user) {
        jdbcUserDao.updateLoginInfo(user);
    }

    public void insertUser(User user) {
        jdbcUserDao.insertUser(user);
    }
}
