package io.github.josephpei.DAO;

import io.github.josephpei.domain.Spittle;
import io.github.josephpei.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class HibSpittleDao implements SpittleDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Spittle> getRecentSpittles(int spittlesNumber) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Spittle order by pushTime desc");
        q.setMaxResults(spittlesNumber);
        return q.list();
    }

    @Override
    public List<Spittle> getSpittlesForUser(User user) {
        String hql = "from Spittle where user=:user order by pushTime desc";
        Query q = sessionFactory.getCurrentSession().createQuery(hql);
        q.setParameter("user", user);
        return q.list();
    }

    @Override
    public void addSpittle(Spittle spittle) {
        sessionFactory.getCurrentSession().save(spittle);
    }
}
