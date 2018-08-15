package com.company.user.controller;

import com.company.user.model.UserModel;
import com.company.user.service.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User: zjt
 * DateTime: 2018/2/10 14:41
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     *
     * @param username
     * @param age
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/addUser", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public void addUser(String username, String age, HttpServletResponse response) throws Exception {

        int count = userService.addUser(username, age);

        System.out.println(" ---> " + count);

    }

    /**
     * 删除用户
     *
     * @param id
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/deleteUserById", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public void deleteUserById(String id, HttpServletResponse response) throws Exception {

        int count = userService.deleteUserById(id);

        System.out.println(" ---> " + count);

    }

    /**
     * 修改用户信息
     * @param id
     * @param username
     * @param age
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/updateUser", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public void updateUser(String id, String username, String age , HttpServletResponse response) throws Exception {

        int count = userService.updateUser(id , username , age);

        System.out.println(" ---> " + count);

    }

    /**
     * 新增用户
     *
     * @throws Exception
     */
    @RequestMapping(value = "/queryUserList", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public void queryUserList(HttpServletResponse response) throws Exception {

        JSONObject json = new JSONObject();

        List<UserModel> result = userService.queryUserList();
        json.put("result", result);

//        System.out.println(" 12312 --->asd  请问a22222 " + DateUtil.getCurrentDateTime());

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json.toString());

    }

}