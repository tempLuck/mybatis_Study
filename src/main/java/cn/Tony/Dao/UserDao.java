package cn.Tony.Dao;


import cn.Tony.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//用户持久层接口
public interface UserDao {

    /**
     * 查询所有
     * @return
     */


    List<User> findAll();

    /**
     * 保存方法
     */
    void saveUser(User user);

    /**
     * 更新User
     */
    void updateUser(User user);

    /**
     * 根据UserId删除User
     */
    void deleteUser(Integer userId);
}
