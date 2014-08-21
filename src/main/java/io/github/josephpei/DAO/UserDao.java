package io.github.josephpei.DAO;


import io.github.josephpei.domain.User;

import java.util.List;

public interface UserDao {
    public Long getMatchCount(String userName, String password);
    public User findUserByName(final String userName);
    public List<User> getAllUsers();
    public void updateLoginInfo(User user);
    public void insertUser(User user);
}
