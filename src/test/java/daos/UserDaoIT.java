package daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import daos.TokenDao;
import daos.UserDao;
import entities.Token;
import entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class UserDaoIT {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

    @Test
    public void testCreate() {
        assertTrue(userDao.count() <= 4);
    }

   // @Test
    /*public void testFindByTokenValue() {
        User user = userDao.findByUsername("admin");
        Token token = tokenDao.findByUser(user);
        System.out.println(token.toString());
        User user2 = userDao.findByTokenValue(token.getValue());
        System.out.println(user2.toString());
        assertEquals(user, userDao.findByTokenValue(token.getValue()));
        assertEquals(null, userDao.findByTokenValue(token.getValue()));
        assertNull(userDao.findByTokenValue("kk"));
    }*/
}
