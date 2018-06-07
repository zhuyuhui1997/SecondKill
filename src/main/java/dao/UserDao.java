package dao;

import entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    /*
    * 根据用户名获取手机号
    *
    * */
    public User getUserByName(@Param("userName") String userName);

    /**
     * 根据用户名获取密码
     * @param userName
     * @return
     */
    public String getPasswordByName(@Param("userName") String userName);

    int insertUser(@Param("user") User user);
}
