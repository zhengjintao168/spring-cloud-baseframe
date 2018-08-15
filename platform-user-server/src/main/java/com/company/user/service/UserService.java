package com.company.user.service;

import com.company.user.model.UserModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: zjt
 * DateTime: 2018/1/31 20:49
 */
public interface UserService {

    int addUser(String username, String age) throws Exception;

    int deleteUserById(String id);

    int updateUser(String id, String username, String age);

    List<UserModel> queryUserList();

}