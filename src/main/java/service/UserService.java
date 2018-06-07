package service;

import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/*
*
* 用户操作服务
*
* */
public interface UserService {
    public String getPassword(String name);
    User getUser(String userName);
}
