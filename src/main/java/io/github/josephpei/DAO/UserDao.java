package io.github.josephpei.DAO;


import io.github.josephpei.domain.User;

public interface UserDao {
    public Integer getMatchCount(String userName, String password);
    public User findUserByName(final String userName);
    public void updateLoginInfo(User user);
    public void insertUser(User user);
}
