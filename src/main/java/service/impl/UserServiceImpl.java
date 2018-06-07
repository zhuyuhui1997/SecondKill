package service.impl;

import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public String getPassword(String name) {
        String password = userDao.getPasswordByName(name);
        return password;
    }

    @Override
    public User getUser(String userName) {
        User user = userDao.getUserByName(userName);
        return user;
    }
}
