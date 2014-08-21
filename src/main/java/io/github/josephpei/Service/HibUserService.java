package io.github.josephpei.Service;

import io.github.josephpei.DAO.HibUserDao;
import io.github.josephpei.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HibUserService {
    @Autowired
    private HibUserDao hibUserDao;

    @Transactional
    public boolean hasMatchUser(String userName, String password) {
        Long count = hibUserDao.getMatchCount(userName, password);
        return count > 0;
    }

    @Transactional
    public User findUserByName(String userName) {
        return hibUserDao.findUserByName(userName);
    }

    @Transactional
    public List<User> getAllUsers() {
        return hibUserDao.getAllUsers();
    }

    @Transactional
    public void loginSuccess(User user) {
        hibUserDao.updateLoginInfo(user);
    }

    @Transactional
    public void insertUser(User user) {
        hibUserDao.insertUser(user);
    }
}
