package com.zizhuo.weatherdemo.dao;

import com.zizhuo.weatherdemo.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {


    @Select("select * from tbl_user where username=#{username}")
    User findByIdNameAndPwd(@Param("username") String username);

    @Select("select * from tbl_user")
    List<User> findAll();

    @Delete("delete from tbl_user where id=#{id}")
    int deleteById(@Param("id") int id);

    @Insert("insert into tbl_user(username,password) values (#{username},#{password})")
    int insertUser(User user);

    @Select("select * from tbl_user where id=#{id}")
    User findById(@Param("id") int  id);

    @Update("update tbl_user set username=#{username},password=#{password} where id=#{id}")
    int updateById(User user);
}
