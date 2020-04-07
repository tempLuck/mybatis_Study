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

    @Select("select * from user")
    List<User> findAll();

}
