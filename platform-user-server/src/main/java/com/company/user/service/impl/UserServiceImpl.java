package com.company.user.service.impl;

import com.company.user.dao.UserDao;
import com.company.user.model.UserModel;
import com.company.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * User: zjt
 * DateTime: 2018/1/31 20:49
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int addUser(String username, String age) throws Exception {

        int count = 0;

        count += userDao.addUser(username, age);

        if (count > 0) {
            throw new Exception("出错啦，事务回滚！");
        }

        count += userDao.addUser(username + "22", age);

        return count;

        //userDao.addUser(username , age);

    }

    @Override
    public int deleteUserById(String id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public int updateUser(String id, String username, String age) {
        return userDao.updateUser(id , username , age);
    }

    @Override
    public List<UserModel> queryUserList() {
        return userDao.queryUserList();
    }

}