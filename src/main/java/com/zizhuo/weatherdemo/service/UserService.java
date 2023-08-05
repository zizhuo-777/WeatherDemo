package com.zizhuo.weatherdemo.service;

import com.zizhuo.weatherdemo.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    User findById(int  id);

    int updateById(User user);

    int insertUser(User user);

    int deleteById( int id);

    List<User> findAll();

    User login(String username,String password);
}
