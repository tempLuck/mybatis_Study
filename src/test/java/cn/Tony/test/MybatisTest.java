package cn.Tony.test;


import cn.Tony.Dao.UserDao;
import cn.Tony.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before //用于在测试方法执行之前执行
    public void init()throws Exception{
        // 1.读取配置文件
        String resource = "Mybatis-Config.xml";
        in = Resources.getResourceAsStream(resource);
        // 2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory =builder.build(in);
        //3使用工厂生产SqlSession对象
        sqlSession = factory.openSession();
        // 4.使用SqlSession创建Dao接口的代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy() throws Exception{
        //提交事务
        sqlSession.commit();
        // 6.释放资源
        sqlSession.close();
        in.close();

    }
    @Test
    public void testFindAll() throws IOException {

//        5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user:users){
            System.out.println(user);
        }
    }
    /**
     * 测试保存操作
     */
    @Test
    public void saveUser() throws Exception{

        MybatisTest mybatisTest = new MybatisTest();
        User user = new User();
        user.setUsername("mybatis_saveUser方法");
        user.setAddress("贵州黔西南");
        user.setBirthday(new Date());
        user.setSex("男");
        // 5.使用代理对象执行 保存 方法
        userDao.saveUser(user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void updateUser() {
        User user = new User();
        user.setId(5);
        user.setUsername("MUpdateUser方法");
        user.setSex("女");
        user.setBirthday(new Date());
        user.setAddress("贵州六盘水");

        userDao.updateUser(user);
    }

    /**
     * 根据UserId删除User测试
     */
    @Test
    public void  deleteUser(){
        userDao.deleteUser(6);
    }

}
