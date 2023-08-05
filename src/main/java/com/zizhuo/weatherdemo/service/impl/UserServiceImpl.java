package com.zizhuo.weatherdemo.service.impl;

import com.zizhuo.weatherdemo.bean.User;
import com.zizhuo.weatherdemo.dao.UserDao;
import com.zizhuo.weatherdemo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public int updateById(User user) {
        return userDao.updateById(user);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int deleteById(int id) {
        return userDao.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    //对帐号进行校验，业务操作
    @Override
    public User login(String username, String password) {

        User user = userDao.findByIdNameAndPwd(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
