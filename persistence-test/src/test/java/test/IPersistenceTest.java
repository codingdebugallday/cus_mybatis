package test;

import java.io.InputStream;
import java.util.List;

import com.github.codingdebugallday.cus.mybatis.dao.IUserDao;
import com.github.codingdebugallday.cus.mybatis.io.Resources;
import com.github.codingdebugallday.cus.mybatis.pojo.User;
import com.github.codingdebugallday.cus.mybatis.sqlsession.SqlSession;
import com.github.codingdebugallday.cus.mybatis.sqlsession.SqlSessionFactory;
import com.github.codingdebugallday.cus.mybatis.sqlsession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/8/20 23:21
 * @since 1.0.0
 */
public class IPersistenceTest {

    private IUserDao userDao;

    @Before
    public void before() throws DocumentException {
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @Test
    public void testSelect() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("tom");
        // User result = sqlSession.selectOne("com.github.codingdebugallday.cus.mybatis.dao.IUserDao.findByCondition", user);
        // Assert.assertNotNull(result);
        // List<User> userList = sqlSession.selectList("com.github.codingdebugallday.cus.mybatis.dao.IUserDao.findAll");
        // Assert.assertNotNull(userList);
        List<User> all = userDao.findAll();
        Assert.assertNotNull(all);
        User condition = userDao.findByCondition(user);
        Assert.assertNotNull(condition);
    }

    @Test
    public void testInsert() throws Exception {
        User user = new User();
        user.setId(3L);
        user.setUsername("mary");
        int insert = userDao.insert(user);
        Assert.assertEquals(1L, insert);
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("coder");
        int update = userDao.update(user);
        Assert.assertEquals(1L, update);
    }

    @Test
    public void testDelete() throws Exception {
        int delete = userDao.delete(3L);
        Assert.assertEquals(1L, delete);
    }

}
