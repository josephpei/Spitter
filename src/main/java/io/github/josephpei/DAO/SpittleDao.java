package io.github.josephpei.DAO;


import io.github.josephpei.domain.Spittle;
import io.github.josephpei.domain.User;

import java.util.List;

public interface SpittleDao {
    public List<Spittle> getRecentSpittles(int spittlesNumber);
    public List<Spittle> getSpittlesForUser(User user);
    public void addSpittle(Spittle spittle);
}
