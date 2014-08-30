package io.github.josephpei.Service;


import io.github.josephpei.DAO.HibSpittleDao;
import io.github.josephpei.domain.Spittle;
import io.github.josephpei.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HibSpittleService {
    @Autowired
    private HibSpittleDao hibSpittleDao;

    @Transactional
    public List<Spittle> getRecentSpittles(int spittlesNumber) {
        return hibSpittleDao.getRecentSpittles(spittlesNumber);
    }

    @Transactional
    public List<Spittle> getSpittlesForUser(User user) {
        return hibSpittleDao.getSpittlesForUser(user);
    }

    @Transactional
    public void addSpittle(Spittle spittle) {
        hibSpittleDao.addSpittle(spittle);
    }

    @Transactional
    public void delSpittleById(Long id) {
        Spittle spittle = hibSpittleDao.getSpittleById(id);
        hibSpittleDao.delSpittle(spittle);
    }
}
