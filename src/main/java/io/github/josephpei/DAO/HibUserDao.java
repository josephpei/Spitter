package io.github.josephpei.DAO;

import io.github.josephpei.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;


@Repository
@SuppressWarnings("unchecked")
public class HibUserDao implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long getMatchCount(String userName, String password) {
        String hql = "select count(*) from User where userName=:name and password=:pass";

        Query q = sessionFactory.getCurrentSession().createQuery(hql);
        q.setParameter("name", userName);
        q.setParameter("pass", password);

        Long row = 0L;
        for (Iterator it = q.iterate(); it.hasNext(); )
            row = (Long) it.next();
        return row;
    }

    @Override
    public User findUserByName(String userName) {
        Query q = sessionFactory.getCurrentSession().createQuery("from User where userName=:name");
        q.setParameter("name", userName);
        List<User> users = q.list();
        return (users.isEmpty() ? null : users.get(0));
    }

    @Override
    public List<User> getAllUsers() {
        Query q = sessionFactory.getCurrentSession().createQuery("from User");
        return q.list();
    }

    @Override
    public void updateLoginInfo(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void insertUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }
}
