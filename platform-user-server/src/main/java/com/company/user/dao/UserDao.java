package com.company.user.dao;

import com.company.user.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: zjt
 * DateTime: 2018/2/6 14:05
 */
@Mapper
public interface UserDao {

    int addUser(@Param("username") String username, @Param("age") String age);

    int deleteUserById(@Param("id") String id);

    int updateUser(@Param("id") String id , @Param("username") String username, @Param("age") String age);

    List<UserModel> queryUserList();

}