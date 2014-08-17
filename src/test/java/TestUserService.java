import io.github.josephpei.Service.UserService;
import io.github.josephpei.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spitter-servlet.xml"})
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void hasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        Assert.assertTrue(b1);
        Assert.assertFalse(b2);
    }

    @Test
    public void findUserByName() {
        User user = userService.findUserByName("admin");
        Assert.assertEquals(user.getUserName(), "admin");
    }
}
